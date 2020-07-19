<template>
  <div id="wrap">
    <div id="chart-wrap">
      <div id="chart-diagram" style="border: none;width:100%; height: 90vh"></div>
    </div>
  </div>
</template>
<script>
import go from "gojs";
const MAKE = go.GraphObject.make;
export default {
  name: "displayview",
  data() {
    return {
      loadFlow: undefined,
      animateFlowPath: undefined,
      clueid: undefined
    };
  },
  mounted() {
    var G = go.GraphObject.make;
    var _this = {};
    var _displayer = {};

    /** --------public method----------------------------------------**/

    /**
     * 显示流程图
     * @param flowData  流程图json数据
     */
    this.loadFlow = function(flowData) {
      if (!flowData) return;

      _displayer.model = go.Model.fromJson(flowData);

      var pos = _displayer.model.modelData.position;
      if (pos) _displayer.initialPosition = go.Point.parse(pos);

      // 更改所有连线中间的文本背景色
      setLinkTextBg();
    };

    /**
     * 获取流程图数据
     * @returns {*}
     */
    this.getFlowData = function() {
      _displayer.model.modelData.position = go.Point.stringify(
        _displayer.position
      );
      return _displayer.model.toJson();
    };

    /**
     * 动画显示流程路径状态
     */
    this.animateFlowPath = function(strStepKeys, isCompleted) {
      var stepKeys = strStepKeys.split(",");

      // 查找所有【已完成】步骤：【开始】-> 【已完成】（N个）
      var steps = findFinishedSteps(stepKeys, isCompleted);

      // 高亮所有“已完成”步骤
      showFinishedNodes(steps);

      //【开始】-> 【已完成】（N个）->【待处理】
      // 或
      //【开始】-> 【已完成】（N个）->【结束】
      var lastStep = findLastStep(stepKeys, steps, isCompleted);
      steps.push(lastStep);

      if (!isCompleted) {
        // “待处理”步骤，加上闪烁动画
        loopRunningNode(lastStep);
      }

      // 在连线上加闪烁动画
      var links = findFinishedLinks(steps);
      loopLinks(links);
    };
    /** --------private method----------------------------------------**/

    /**
     * 初始化流程设计器
     * @param divId 设计器Div
     */
    function init(divId) {
      _displayer = G(go.Diagram, divId, {
        allowDrop: false,
        allowSelect: true,
        allowHorizontalScroll: true,
        allowVerticalScroll: true,
        allowMove: false,
        allowLink: false,
        allowRelink: false,
        "draggingTool.dragsLink": false
      });

      // 流程步骤的样式模板
      _displayer.nodeTemplate = makeNodeTemplate();

      // 流程连接线的样式模板
      _displayer.linkTemplate = makeLinkTemplate();
    }

    /**
     * 步骤图的样式模板
     * @returns {*}
     */
    function makeNodeTemplate() {
      return G(
        go.Node,
        "Spot",
        { locationSpot: go.Spot.Center },
        new go.Binding("location", "loc", go.Point.parse).makeTwoWay(
          go.Point.stringify
        ),
        {
          selectable: true,
          selectionAdornmentTemplate: makeNodeSelectionAdornmentTemplate()
        },
        new go.Binding("angle").makeTwoWay(),
        G(
          go.Panel,
          "Auto",
          { name: "PANEL" },
          new go.Binding("desiredSize", "size", go.Size.parse).makeTwoWay(
            go.Size.stringify
          ),
          G(
            go.Shape,
            "RoundedRectangle", // default figure
            {
              portId: "", // the default port: if no spot on link data, use closest side
              name: "PIPE",
              fromLinkable: true,
              toLinkable: true,
              cursor: "pointer",
              fill: "#7e7e7f", // default color
              strokeWidth: 1,
              stroke: "#DDDDDD"
            },
            new go.Binding("figure"),
            new go.Binding("stroke"),
            new go.Binding("strokeDashArray"),
            new go.Binding("strokeWidth"),
            new go.Binding("fill")
          ),
          G(
            go.TextBlock,
            {
              font: "11pt Helvetica, Arial, sans-serif",
              margin: 8,
              maxSize: new go.Size(160, NaN),
              wrap: go.TextBlock.WrapFit,
              stroke: "white"
            },
            new go.Binding("text").makeTwoWay()
          ),
          {
            toolTip: G(
              go.Adornment,
              "Auto",
              G(go.Shape, { fill: "#FFFFCC" }),
              G(
                go.TextBlock,
                { margin: 4 },
                new go.Binding("text", "", nodeInfo)
              )
            )
          }
        )
      );
    }

    /**
     * 选中节点的样式
     * @returns {*}
     */
    function makeNodeSelectionAdornmentTemplate() {
      return G(
        go.Adornment,
        "Auto",
        G(go.Shape, {
          fill: null,
          stroke: "deepskyblue",
          strokeWidth: 1.5,
          strokeDashArray: [4, 2]
        }),
        G(go.Placeholder)
      );
    }

    /**
     * tooltip上显示的信息
     * @param d
     * @returns {string}
     */
    function nodeInfo(d) {
      if (!d.key) return "无key";
      return "编号：" + d.key;
    }

    /**
     * 定义连接线的样式模板
     * @returns {*}
     */
    function makeLinkTemplate() {
      return G(
        go.Link,
        { selectable: false },
        { relinkableFrom: true, relinkableTo: true, reshapable: true },
        {
          routing: go.Link.AvoidsNodes,
          curve: go.Link.JumpOver,
          corner: 5,
          toShortLength: 4
        },
        new go.Binding("points").makeTwoWay(),
        new go.Binding("layerName", "color"),
        new go.Binding("zOrder"),
        G(
          go.Shape,
          { isPanelMain: true, stroke: "black", strokeWidth: 1 },
          new go.Binding("stroke"),
          new go.Binding("zOrder")
        ),
        //G(go.Shape, { isPanelMain: true, stroke: "gray", strokeWidth: 2 }),
        // G(go.Shape, { isPanelMain: true, stroke: "black", strokeWidth: 1, name: "PIPE", strokeDashArray: [10, 10] }),
        G(
          go.Shape,
          { toArrow: "standard", stroke: null },
          new go.Binding("stroke"),
          new go.Binding("fill"),
          new go.Binding("zOrder")
        ),
        G(
          go.Panel,
          "Auto",
          G(
            go.Shape,
            {
              fill: null,
              stroke: null
            },
            new go.Binding("fill", "pFill"),
            new go.Binding("zOrder")
          ),
          G(
            go.TextBlock,
            {
              textAlign: "center",
              font: "10pt helvetica, arial, sans-serif",
              stroke: "#555555",
              margin: 4
            },
            new go.Binding("text", "text"),
            new go.Binding("zOrder")
          )
        )
      );
    }

    /**
     * 返回所有【已完成】的步骤
     * @param stepKeys
     * @param isCompleted
     * @returns {Array}
     */
    function findFinishedSteps(stepKeys, isCompleted) {
      var arrStep = [];
      if (!stepKeys) return arrStep;

      var startStep = findStartStep(); // 【开始】步骤
      arrStep.push(startStep);

      // 【已完成】的步骤
      var finishedCount = stepKeys.length - 1; // 不包含最后一个“待处理“步骤
      if (isCompleted) {
        finishedCount = stepKeys.length; // 包含所有步骤
      }
      for (var i = 0; i < finishedCount; i++) {
        var stepKey = stepKeys[i];
        var step = _displayer.findNodeForKey(stepKey);
        if (!step) continue;

        arrStep.push(step);
      }

      return arrStep;
    }

    /**
     * 高亮“已完成”步骤
     * @param steps
     */
    function showFinishedNodes(steps) {
      if (!steps) return;

      for (var i = 0; i < steps.length; i++) {
        var step = steps[i];

        // 步骤
        _displayer.startTransaction("vacate");
        _displayer.model.setDataProperty(step.data, "fill", "#4fba4f");
        _displayer.commitTransaction("vacate");
      }
    }

    /**
     *
     * 查找【开始】节点
     * @param {} steps
     * @returns {}
     */
    function findStartStep() {
      var startStep = null;
      _displayer.nodes.each(function(step) {
        if (step.data.hasOwnProperty("stepType") && step.data.stepType == 1) {
          startStep = step;
          return false;
        }
      });
      return startStep;
    }

    /**
     * 循环闪烁“已完成”步骤之间的连线
     * @param links
     */
    function loopLinks(links) {
      //setTimeout(function () {
      //    showFinishedLinks(links);// “已完成”连线
      //    loopLinks(links);
      //}, 200);
      showFinishedLinks(links);
    }

    /**
     * 循环闪烁“待处理”步骤
     * @param node
     */
    function loopRunningNode(node) {
      setTimeout(function() {
        showRunningNode(node);
        loopRunningNode(node);
      }, 200);
    }

    /**
     * 高亮“待处理”步骤
     * @param node
     */
    function showRunningNode(node) {
      if (!node) return;

      _displayer.startTransaction("vacate");
      //_displayer.model.setDataProperty(node.data, "fill", (node.data.fill === "#ff9001") ? "#ffB001" : "#ff9001");
      _displayer.model.setDataProperty(node.data, "fill", "#ffB001");
      _displayer.commitTransaction("vacate");

      // 边框加上流水动画
      var shape = node.findObject("PIPE");
      var off = shape.strokeDashOffset - 2;
      shape.strokeDashOffset = off <= 0 ? 20 : off;
    }

    /**
     * 获取最后一个步骤（【待处理】或【结束】）
     * @param stepKeys
     * @param steps
     * @param isCompleted
     * @returns {*}
     */
    function findLastStep(stepKeys, steps, isCompleted) {
      var lastStep;
      if (!isCompleted) {
        // 获取“待处理”步骤
        var lastKey = stepKeys[stepKeys.length - 1];
        var step = _displayer.findNodeForKey(lastKey);
        _displayer.startTransaction("vacate");
        _displayer.model.setDataProperty(step.data, "stroke", "red");
        //_displayer.model.setDataProperty(step.data, "strokeWidth", 2);
        _displayer.model.setDataProperty(step.data, "strokeDashArray", [
          10,
          10
        ]);
        _displayer.commitTransaction("vacate");

        //【开始】-> 【已完成】（N个）->【待处理】
        lastStep = step;
      } else {
        // 用最后一根连线获取【结束】步骤
        var lastFinishedStep = steps[steps.length - 1];

        var it = lastFinishedStep.findLinksOutOf();
        var lastLink = it.first();
        var endStep = lastLink.toNode;

        //【开始】-> 【已完成】（N个）->【结束】
        lastStep = endStep;
      }

      return lastStep;
    }

    /**
     * 查找步骤之间的连线
     * @param steps
     * @returns {Array}
     */
    function findFinishedLinks(steps) {
      var arrLinks = [];

      if (!steps || steps.length < 1) return arrLinks;

      var currStep = steps[0]; // 【开始】步骤

      for (var i = 0; i < steps.length; i++) {
        var step = steps[i];

        // 连线
        var link = currStep.findLinksBetween(step).first();
        if (!link) continue;
        arrLinks.push(link);

        currStep = step;
      }

      return arrLinks;
    }

    /**
     * 高亮所有“已完成”步骤的连线
     * @param links
     */
    function showFinishedLinks(links) {
      if (!links) return;

      for (var i = 0; i < links.length; i++) {
        // 连线
        var link = links[i];
        _displayer.startTransaction("vacate");
        //_displayer.model.setDataProperty(link.data, "stroke", (link.data.stroke === "#4fba4f" ? "red" : "#4fba4f"));
        //_displayer.model.setDataProperty(link.data, "fill", (link.data.fill === "#4fba4f" ? "red" : "#4fba4f"));
        _displayer.model.setDataProperty(link.data, "stroke", "#4fba4f");
        _displayer.model.setDataProperty(link.data, "fill", "#4fba4f");
        _displayer.model.setDataProperty(link.data, "zOrder", 999);
        _displayer.commitTransaction("vacate");

        // 置于最上层，防止被遮挡
        _displayer.startTransaction("modified zOrder");
        _displayer.model.setDataProperty(link.data, "zOrder", 1);
        _displayer.commitTransaction("modified zOrder");

        // 连线加上流水动画
        //var shape = link.findObject("PIPE");
        //var off = shape.strokeDashOffset - 2;
        //shape.strokeDashOffset = (off <= 0) ? 20 : off;
      }
    }

    /**
     * 更改所有连线中间的文本背景色
     */
    function setLinkTextBg() {
      _displayer.links.each(function(link) {
        _displayer.startTransaction("vacate");
        if (link.data.text) {
          _displayer.model.setDataProperty(
            link.data,
            "pFill",
            window.go.GraphObject.make(go.Brush, "Radial", {
              0: "rgb(240, 240, 240)",
              0.3: "rgb(240, 240, 240)",
              1: "rgba(240, 240, 240, 0)"
            })
          );
        }
        _displayer.commitTransaction("vacate");
      });
    }

    /** --------public method-------------end---------------------------**/

    init("chart-diagram");
    this.initWorkflowinfoview();
  },
  created() {
    this.clueid = this.$route.params && this.$route.query.id;
  },
  methods: {
    initWorkflowinfoview() {
      this.loadFlow({
        class: "go.GraphLinksModel",
        linkFromPortIdProperty: "fromPort",
        linkToPortIdProperty: "toPort",
        modelData: { position: "-99.56136576575553 -453.7175926208496" },
        nodeDataArray: [
          {
            key: "1",
            text: "开始",
            figure: "circle",
            fill: "#4fba4f",
            stepType: "1",
            loc: "-50 -380"
          },
          {
            key: "8feb436d-69f7-416c-ba72-7d08ce5ba282",
            text: "使用数据查询措施审批",
            loc: "110 -380"
          },
          {
            key: "a4ffb09f-759c-4186-bf02-853c7b8093a1",
            text: "承办室审批",
            loc: "110 -310"
          },
          {
            key: "a5b70f45-b54e-4455-9024-53e74bd94fba",
            text: "分管常委/委员审批",
            loc: "110 -250"
          },
          {
            key: "b937ca3f-e708-4813-b949-f34594199fda",
            text: "分管副书记/副主任审批",
            loc: "110 -190"
          },
          {
            key: "011f4d21-eb05-4f70-b1e6-5efdb463ed2b",
            text: "书记/主任审批",
            loc: "110 -140"
          },
          {
            key: "215770ba-945c-44ae-bc39-3001d593af43",
            text: "委托数据查询",
            loc: "300 -380"
          },
          {
            key: "9f367767-bc18-432a-aad0-98a2acc9b690",
            text: "发给案管室",
            loc: "300 -310"
          },
          {
            key: "a248ed29-76fc-4c58-acb0-24fb37c3c473",
            text: "监督审查技术部门受理",
            loc: "450 -380"
          },
          {
            key: "9250b704-acc1-41f4-b4a6-220fcfab2d2e",
            text: "监督审查技术部门审批",
            loc: "450 -310"
          },
          {
            key: "675b3222-e86e-45eb-ab07-158f7077b1d4",
            text: "监督审查技术部门办理",
            loc: "660 -380"
          },
          {
            key: "02df032b-b88b-41d5-b299-cf7b059edf35",
            text: "反馈委托部门",
            loc: "820 -380"
          },
          {
            key: "2",
            text: "结束",
            figure: "circle",
            fill: "#CE0620",
            stepType: "4",
            loc: "920 -380"
          }
        ],
        linkDataArray: [
          {
            from: "8feb436d-69f7-416c-ba72-7d08ce5ba282",
            to: "a4ffb09f-759c-4186-bf02-853c7b8093a1",
            fromPort: null,
            toPort: null,
            points: [
              110,
              -360.8008519466466,
              110,
              -350.8008519466466,
              110,
              -344.9999999999999,
              110,
              -344.9999999999999,
              110,
              -339.19914805335316,
              110,
              -329.19914805335316
            ]
          },
          {
            from: "b937ca3f-e708-4813-b949-f34594199fda",
            to: "011f4d21-eb05-4f70-b1e6-5efdb463ed2b",
            fromPort: null,
            toPort: null,
            points: [
              109.99999999999999,
              -170.80085194664682,
              109.99999999999999,
              -160.80085194664682,
              110.00000000000003,
              -160.80085194664682,
              110.00000000000003,
              -169.1991480533532,
              110.00000000000006,
              -169.1991480533532,
              110.00000000000006,
              -159.1991480533532
            ]
          },
          {
            from: "8feb436d-69f7-416c-ba72-7d08ce5ba282",
            to: "215770ba-945c-44ae-bc39-3001d593af43",
            fromPort: null,
            toPort: null,
            points: [
              194.5613657657555,
              -379.99999999999983,
              204.5613657657555,
              -379.99999999999983,
              219.65998840332028,
              -379.99999999999983,
              219.65998840332028,
              -379.9999999999996,
              234.75861104088506,
              -379.9999999999996,
              244.75861104088506,
              -379.9999999999996
            ]
          },
          {
            from: "215770ba-945c-44ae-bc39-3001d593af43",
            to: "9f367767-bc18-432a-aad0-98a2acc9b690",
            fromPort: null,
            toPort: null,
            points: [
              299.99999999999983,
              -360.8008519466466,
              299.99999999999983,
              -350.8008519466466,
              299.99999999999983,
              -344.99999999999983,
              299.99999999999966,
              -344.99999999999983,
              299.99999999999966,
              -339.19914805335304,
              299.99999999999966,
              -329.19914805335304
            ]
          },
          {
            from: "215770ba-945c-44ae-bc39-3001d593af43",
            to: "a248ed29-76fc-4c58-acb0-24fb37c3c473",
            fromPort: null,
            toPort: null,
            points: [
              355.2413889591149,
              -379.9999999999998,
              365.2413889591149,
              -379.9999999999998,
              365.2413889591149,
              -379.9999999999998,
              355.4386342342446,
              -379.9999999999998,
              355.4386342342446,
              -379.9999999999998,
              365.4386342342446,
              -379.9999999999998
            ]
          },
          {
            from: "a248ed29-76fc-4c58-acb0-24fb37c3c473",
            to: "9250b704-acc1-41f4-b4a6-220fcfab2d2e",
            fromPort: null,
            toPort: null,
            points: [
              449.9999999999999,
              -360.8008519466466,
              449.9999999999999,
              -350.8008519466466,
              449.9999999999999,
              -344.99999999999983,
              449.9999999999999,
              -344.99999999999983,
              449.9999999999999,
              -339.19914805335304,
              449.9999999999999,
              -329.19914805335304
            ]
          },
          {
            from: "9250b704-acc1-41f4-b4a6-220fcfab2d2e",
            to: "675b3222-e86e-45eb-ab07-158f7077b1d4",
            fromPort: null,
            toPort: null,
            points: [
              534.5613657657557,
              -310,
              544.5613657657557,
              -310,
              555,
              -310,
              555,
              -379.9999999999998,
              565.4386342342443,
              -379.9999999999998,
              575.4386342342443,
              -379.9999999999998
            ]
          },
          {
            from: "675b3222-e86e-45eb-ab07-158f7077b1d4",
            to: "02df032b-b88b-41d5-b299-cf7b059edf35",
            fromPort: null,
            toPort: null,
            points: [
              744.5613657657553,
              -379.99999999999983,
              754.5613657657553,
              -379.99999999999983,
              754.6599884033202,
              -379.99999999999983,
              754.6599884033202,
              -379.99999999999983,
              754.758611040885,
              -379.99999999999983,
              764.758611040885,
              -379.99999999999983
            ]
          },
          {
            from: "a248ed29-76fc-4c58-acb0-24fb37c3c473",
            to: "675b3222-e86e-45eb-ab07-158f7077b1d4",
            fromPort: null,
            toPort: null,
            points: [
              534.5613657657557,
              -379.9999999999998,
              544.5613657657557,
              -379.9999999999998,
              555,
              -379.9999999999998,
              555,
              -379.9999999999998,
              565.4386342342443,
              -379.9999999999998,
              575.4386342342443,
              -379.9999999999998
            ]
          },
          {
            from: "a5b70f45-b54e-4455-9024-53e74bd94fba",
            to: "b937ca3f-e708-4813-b949-f34594199fda",
            fromPort: "B",
            toPort: "T",
            points: [
              110,
              -230.80085194664682,
              110,
              -220.80085194664682,
              110,
              -220,
              109.99999999999999,
              -220,
              109.99999999999999,
              -219.19914805335318,
              109.99999999999999,
              -209.19914805335318
            ]
          },
          {
            from: "a4ffb09f-759c-4186-bf02-853c7b8093a1",
            to: "a5b70f45-b54e-4455-9024-53e74bd94fba",
            fromPort: "B",
            toPort: "T",
            points: [
              110,
              -290.8008519466468,
              110,
              -280.8008519466468,
              110,
              -280,
              110,
              -280,
              110,
              -279.19914805335316,
              110,
              -269.19914805335316
            ]
          },
          {
            from: "a5b70f45-b54e-4455-9024-53e74bd94fba",
            to: "215770ba-945c-44ae-bc39-3001d593af43",
            fromPort: "R",
            toPort: "L",
            points: [
              181.93788401526723,
              -250,
              191.93788401526723,
              -250,
              194,
              -250,
              194,
              -250,
              234,
              -250,
              234,
              -379.9999999999998,
              234.75861104088506,
              -379.9999999999998,
              244.75861104088506,
              -379.9999999999998
            ]
          },
          {
            from: "b937ca3f-e708-4813-b949-f34594199fda",
            to: "215770ba-945c-44ae-bc39-3001d593af43",
            fromPort: "R",
            toPort: "L",
            points: [
              196.59787241858754,
              -190,
              206.59787241858754,
              -190,
              210,
              -190,
              210,
              -190,
              234,
              -190,
              234,
              -379.9999999999998,
              234.75861104088506,
              -379.9999999999998,
              244.75861104088506,
              -379.9999999999998
            ]
          },
          {
            from: "011f4d21-eb05-4f70-b1e6-5efdb463ed2b",
            to: "215770ba-945c-44ae-bc39-3001d593af43",
            fromPort: "R",
            toPort: "L",
            points: [
              167.27789561194697,
              -140.00000000000003,
              177.27789561194697,
              -140.00000000000003,
              178,
              -140.00000000000003,
              178,
              -140.00000000000003,
              234,
              -140.00000000000003,
              234,
              -379.9999999999998,
              234.75861104088506,
              -379.9999999999998,
              244.75861104088506,
              -379.9999999999998
            ]
          },
          {
            from: "1",
            to: "8feb436d-69f7-416c-ba72-7d08ce5ba282",
            fromPort: "",
            toPort: "L",
            points: [
              -16.563970344011167,
              -379.99999999999994,
              -6.563970344011167,
              -379.99999999999994,
              4.437331945116654,
              -379.99999999999994,
              4.437331945116654,
              -380,
              15.438634234244475,
              -380,
              25.438634234244475,
              -380
            ]
          },
          {
            from: "02df032b-b88b-41d5-b299-cf7b059edf35",
            to: "2",
            fromPort: "",
            toPort: "L",
            points: [
              875.2413889591146,
              -379.9999999999998,
              885.2413889591146,
              -379.9999999999998,
              885.2413889591146,
              -379.9999999999999,
              876.5639703440113,
              -379.9999999999999,
              876.5639703440113,
              -379.99999999999994,
              886.5639703440113,
              -379.99999999999994
            ]
          },
          {
            from: "a4ffb09f-759c-4186-bf02-853c7b8093a1",
            to: "215770ba-945c-44ae-bc39-3001d593af43",
            fromPort: "R",
            toPort: "L",
            points: [
              157.91139475745476,
              -310,
              167.91139475745476,
              -310,
              233.421875,
              -310,
              233.421875,
              -379.9999999999998,
              234.75861104088506,
              -379.9999999999998,
              244.75861104088506,
              -379.9999999999998
            ]
          }
        ]
      });
      this.animateFlowPath(
        "8feb436d-69f7-416c-ba72-7d08ce5ba282,a5b70f45-b54e-4455-9024-53e74bd94fba",
        false
      );
    }
  }
};
</script>
