
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[ResponseEventBase,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(response: ResponseEventBase)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.46*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>
    """),_display_(/*6.6*/head()),format.raw/*6.12*/("""
"""),format.raw/*7.1*/("""</head>

<body style="height:100%">

    """),_display_(/*11.6*/navbar()),format.raw/*11.14*/("""

    """),format.raw/*13.5*/("""<div class="container-fluid">
        <div class="row">
            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <span data-feather="home"></span>
                                Applications <span class="sr-only">(current)</span>
                                <div id="appnav"></div>
                                <div id="menunav"></div>
                                <div id="tree1"></div>
                            </a>
                        </li>
                    </ul>

                </div>
            </nav>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                """),_display_(/*33.18*/content),format.raw/*33.25*/("""
            """),format.raw/*34.13*/("""</main>
        </div>
    </div>

    <script>

      function createElement(i, name,context) """),format.raw/*40.47*/("""{"""),format.raw/*40.48*/("""
        """),format.raw/*41.9*/("""return $('<div>', """),format.raw/*41.27*/("""{"""),format.raw/*41.28*/("""
          """),format.raw/*42.11*/("""html: $('<a>', """),format.raw/*42.26*/("""{"""),format.raw/*42.27*/("""
            """),format.raw/*43.13*/("""href: context,
            text: "["+name+"]"
          """),format.raw/*45.11*/("""}"""),format.raw/*45.12*/(""")
        """),format.raw/*46.9*/("""}"""),format.raw/*46.10*/(""")
      """),format.raw/*47.7*/("""}"""),format.raw/*47.8*/("""

      """),format.raw/*49.7*/("""/*$.get( "/_root/apps", function( data ) """),format.raw/*49.48*/("""{"""),format.raw/*49.49*/("""
        """),format.raw/*50.9*/("""var c=$("#appnav");
        $.each(data.applications, function(i, obj) """),format.raw/*51.52*/("""{"""),format.raw/*51.53*/("""
          """),format.raw/*52.11*/("""c.append(createElement(1, obj.name, obj.context));
        """),format.raw/*53.9*/("""}"""),format.raw/*53.10*/(""");
      """),format.raw/*54.7*/("""}"""),format.raw/*54.8*/(""", 'json');*/

      $.get( "/_root/menu", function( data ) """),format.raw/*56.46*/("""{"""),format.raw/*56.47*/("""
        """),format.raw/*57.9*/("""var json = JSON.parse("[" + JSON.stringify(data) + "]")
        $("#tree1").tree("""),format.raw/*58.26*/("""{"""),format.raw/*58.27*/("""
              """),format.raw/*59.15*/("""data: json,
              saveState: 'skysail-nav-tree'
        """),format.raw/*61.9*/("""}"""),format.raw/*61.10*/(""");
        $('#tree1').on('tree.click', function(event) """),format.raw/*62.54*/("""{"""),format.raw/*62.55*/("""
            """),format.raw/*63.13*/("""//console.log("event", event.node);
            var node = event.node;
            window.location.href = node.url;
        """),format.raw/*66.9*/("""}"""),format.raw/*66.10*/(""");
      """),format.raw/*67.7*/("""}"""),format.raw/*67.8*/(""");


    </script>

</body>

</html>
"""))
      }
    }
  }

  def render(response:ResponseEventBase,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(response)(content)

  def f:((ResponseEventBase) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (response) => (content) => apply(response)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Apr 04 08:06:40 CEST 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/main.scala.html
                  HASH: 5de566f651bc1b6c8024ca78c164a287b7382039
                  MATRIX: 466->1|605->45|633->47|693->82|719->88|746->89|814->131|843->139|876->145|1781->1023|1809->1030|1850->1043|1973->1138|2002->1139|2038->1148|2084->1166|2113->1167|2152->1178|2195->1193|2224->1194|2265->1207|2349->1263|2378->1264|2415->1274|2444->1275|2479->1283|2507->1284|2542->1292|2611->1333|2640->1334|2676->1343|2775->1414|2804->1415|2843->1426|2929->1485|2958->1486|2994->1495|3022->1496|3109->1555|3138->1556|3174->1565|3283->1646|3312->1647|3355->1662|3446->1726|3475->1727|3559->1783|3588->1784|3629->1797|3780->1921|3809->1922|3845->1931|3873->1932
                  LINES: 12->1|17->1|19->3|22->6|22->6|23->7|27->11|27->11|29->13|49->33|49->33|50->34|56->40|56->40|57->41|57->41|57->41|58->42|58->42|58->42|59->43|61->45|61->45|62->46|62->46|63->47|63->47|65->49|65->49|65->49|66->50|67->51|67->51|68->52|69->53|69->53|70->54|70->54|72->56|72->56|73->57|74->58|74->58|75->59|77->61|77->61|78->62|78->62|79->63|82->66|82->66|83->67|83->67
                  -- GENERATED --
              */
          