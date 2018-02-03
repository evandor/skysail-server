
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object NotesResource_Entity extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*8.2*/render/*8.8*/(json: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.26*/("""
  """),format.raw/*9.3*/("""render(json)
  "hi"
""")))};
Seq[Any](format.raw/*6.68*/("""

"""),format.raw/*11.2*/("""

"""),_display_(/*13.2*/main(response)/*13.16*/ {_display_(Seq[Any](format.raw/*13.18*/("""

"""),format.raw/*15.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1><a href="/demo/v1/notes">Notes</a> &gt; """),_display_(/*19.54*/rep/*19.57*/.rawData.head.get("id")),format.raw/*19.80*/("""</h1>
        <p class="lead">show note:</p>
        <div>"""),_display_(/*21.15*/rep/*21.18*/.rawData),format.raw/*21.26*/("""</div>
        <hr>
        <div>"""),_display_(/*23.15*/rep/*23.18*/.response.entity),format.raw/*23.34*/("""</div>
        <hr>
        <div>"""),_display_(/*25.15*/rep/*25.18*/.jsonData),format.raw/*25.27*/("""</div>
        <hr>
        <div>"""),_display_(/*27.15*/rep/*27.18*/.json),format.raw/*27.23*/("""</div>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Content</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">"""),_display_(/*37.34*/rep/*37.37*/.rawData.head.get("title")),format.raw/*37.63*/("""</th>
                <td>"""),_display_(/*38.22*/rep/*38.25*/.rawData.head.get("content")),format.raw/*38.53*/("""</td>
            </tr>

            <tr>
                <th colspan="2">
                    <a href='#'>"""),_display_(/*43.34*/render(rep.jsonData)),format.raw/*43.54*/("""</a>
                </th>
            </tr>

            
            <tr>
                <th colspan="2">
                    <a href='"""),_display_(/*50.31*/rep/*50.34*/.rawData.head.get("id")),format.raw/*50.57*/("""/'>edit note</a>
                </th>
            </tr>
            </tbody>

        </table>

        yyyyy
        <dom-element json=""""),_display_(/*58.29*/rep/*58.32*/.jsonData),format.raw/*58.41*/(""""></dom-element>


    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,o:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,o)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,o) => apply(rep,response,o)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Feb 03 09:40:24 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/NotesResource_Entity.scala.html
                  HASH: 45083fe6bd7e755bb3ecc31ec267c6f05eea6499
                  MATRIX: 724->193|868->262|881->268|975->286|1004->289|1064->259|1093->310|1122->313|1145->327|1185->329|1214->331|1367->457|1379->460|1423->483|1509->542|1521->545|1550->553|1611->587|1623->590|1660->606|1721->640|1733->643|1763->652|1824->686|1836->689|1862->694|2145->950|2157->953|2204->979|2258->1006|2270->1009|2319->1037|2454->1145|2495->1165|2661->1304|2673->1307|2717->1330|2883->1469|2895->1472|2925->1481
                  LINES: 16->6|20->8|20->8|22->8|23->9|26->6|28->11|30->13|30->13|30->13|32->15|36->19|36->19|36->19|38->21|38->21|38->21|40->23|40->23|40->23|42->25|42->25|42->25|44->27|44->27|44->27|54->37|54->37|54->37|55->38|55->38|55->38|60->43|60->43|67->50|67->50|67->50|75->58|75->58|75->58
                  -- GENERATED --
              */
          