
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object index4 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<meta charset="utf-8" />
<title>WebSocket Test</title>
<script language="javascript" type="text/javascript">

  //var wsUri = "ws://echo.websocket.org/";
  var wsUri = "ws://localhost:8080/websocket"
  var output;

  function init()
  """),format.raw/*11.3*/("""{"""),format.raw/*11.4*/("""
    """),format.raw/*12.5*/("""output = document.getElementById("output");
    testWebSocket();
  """),format.raw/*14.3*/("""}"""),format.raw/*14.4*/("""

  """),format.raw/*16.3*/("""function testWebSocket()
  """),format.raw/*17.3*/("""{"""),format.raw/*17.4*/("""
    """),format.raw/*18.5*/("""websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) """),format.raw/*19.38*/("""{"""),format.raw/*19.39*/(""" """),format.raw/*19.40*/("""onOpen(evt) """),format.raw/*19.52*/("""}"""),format.raw/*19.53*/(""";
    websocket.onclose = function(evt) """),format.raw/*20.39*/("""{"""),format.raw/*20.40*/(""" """),format.raw/*20.41*/("""onClose(evt) """),format.raw/*20.54*/("""}"""),format.raw/*20.55*/(""";
    websocket.onmessage = function(evt) """),format.raw/*21.41*/("""{"""),format.raw/*21.42*/(""" """),format.raw/*21.43*/("""onMessage(evt) """),format.raw/*21.58*/("""}"""),format.raw/*21.59*/(""";
    websocket.onerror = function(evt) """),format.raw/*22.39*/("""{"""),format.raw/*22.40*/(""" """),format.raw/*22.41*/("""onError(evt) """),format.raw/*22.54*/("""}"""),format.raw/*22.55*/(""";
  """),format.raw/*23.3*/("""}"""),format.raw/*23.4*/("""

  """),format.raw/*25.3*/("""function onOpen(evt)
  """),format.raw/*26.3*/("""{"""),format.raw/*26.4*/("""
    """),format.raw/*27.5*/("""writeToScreen("CONNECTED");
    doSend("WebSocket rocks");
  """),format.raw/*29.3*/("""}"""),format.raw/*29.4*/("""

  """),format.raw/*31.3*/("""function onClose(evt)
  """),format.raw/*32.3*/("""{"""),format.raw/*32.4*/("""
    """),format.raw/*33.5*/("""writeToScreen("DISCONNECTED");
  """),format.raw/*34.3*/("""}"""),format.raw/*34.4*/("""

  """),format.raw/*36.3*/("""function onMessage(evt)
  """),format.raw/*37.3*/("""{"""),format.raw/*37.4*/("""
    """),format.raw/*38.5*/("""writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
    websocket.close();
  """),format.raw/*40.3*/("""}"""),format.raw/*40.4*/("""

  """),format.raw/*42.3*/("""function onError(evt)
  """),format.raw/*43.3*/("""{"""),format.raw/*43.4*/("""
    """),format.raw/*44.5*/("""writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
  """),format.raw/*45.3*/("""}"""),format.raw/*45.4*/("""

  """),format.raw/*47.3*/("""function doSend(message)
  """),format.raw/*48.3*/("""{"""),format.raw/*48.4*/("""
    """),format.raw/*49.5*/("""writeToScreen("SENT: " + message);
    websocket.send(message);
  """),format.raw/*51.3*/("""}"""),format.raw/*51.4*/("""

  """),format.raw/*53.3*/("""function writeToScreen(message)
  """),format.raw/*54.3*/("""{"""),format.raw/*54.4*/("""
    """),format.raw/*55.5*/("""var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
  """),format.raw/*59.3*/("""}"""),format.raw/*59.4*/("""

  """),format.raw/*61.3*/("""window.addEventListener("load", init, false);

  </script>

<h2>WebSocket Test</h2>

<div id="output"></div>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Nov 14 17:01:43 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./resources/templates/io/skysail/core/app/resources/index4.scala.html
                  HASH: 3111a90dd9cc3282c0e79ae7a9c1a3d30f954a18
                  MATRIX: 521->0|799->251|827->252|859->257|953->324|981->325|1012->329|1066->356|1094->357|1126->362|1225->433|1254->434|1283->435|1323->447|1352->448|1420->488|1449->489|1478->490|1519->503|1548->504|1618->546|1647->547|1676->548|1719->563|1748->564|1816->604|1845->605|1874->606|1915->619|1944->620|1975->624|2003->625|2034->629|2084->652|2112->653|2144->658|2232->719|2260->720|2291->724|2342->748|2370->749|2402->754|2462->787|2490->788|2521->792|2574->818|2602->819|2634->824|2763->926|2791->927|2822->931|2873->955|2901->956|2933->961|3032->1033|3060->1034|3091->1038|3145->1065|3173->1066|3205->1071|3298->1137|3326->1138|3357->1142|3418->1176|3446->1177|3478->1182|3643->1320|3671->1321|3702->1325
                  LINES: 16->1|26->11|26->11|27->12|29->14|29->14|31->16|32->17|32->17|33->18|34->19|34->19|34->19|34->19|34->19|35->20|35->20|35->20|35->20|35->20|36->21|36->21|36->21|36->21|36->21|37->22|37->22|37->22|37->22|37->22|38->23|38->23|40->25|41->26|41->26|42->27|44->29|44->29|46->31|47->32|47->32|48->33|49->34|49->34|51->36|52->37|52->37|53->38|55->40|55->40|57->42|58->43|58->43|59->44|60->45|60->45|62->47|63->48|63->48|64->49|66->51|66->51|68->53|69->54|69->54|70->55|74->59|74->59|76->61
                  -- GENERATED --
              */
          