
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object NotesResource_Entity extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*8.2*/render/*8.8*/(json: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.26*/("""
  """),format.raw/*9.3*/("""render(json)
  "hi"
""")))};
Seq[Any](format.raw/*6.57*/("""

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

  def render(rep:RepresentationModel,response:ResponseEventBase): play.twirl.api.HtmlFormat.Appendable = apply(rep,response)

  def f:((RepresentationModel,ResponseEventBase) => play.twirl.api.HtmlFormat.Appendable) = (rep,response) => apply(rep,response)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 15 17:45:23 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/NotesResource_Entity.scala.html
                  HASH: 0e57a284c56e447701c0b61b121bbe16338bca0f
                  MATRIX: 717->193|850->251|863->257|957->275|986->278|1046->248|1075->299|1104->302|1127->316|1167->318|1196->320|1349->446|1361->449|1405->472|1491->531|1503->534|1532->542|1593->576|1605->579|1642->595|1703->629|1715->632|1745->641|1806->675|1818->678|1844->683|2127->939|2139->942|2186->968|2240->995|2252->998|2301->1026|2436->1134|2477->1154|2643->1293|2655->1296|2699->1319|2865->1458|2877->1461|2907->1470
                  LINES: 16->6|20->8|20->8|22->8|23->9|26->6|28->11|30->13|30->13|30->13|32->15|36->19|36->19|36->19|38->21|38->21|38->21|40->23|40->23|40->23|42->25|42->25|42->25|44->27|44->27|44->27|54->37|54->37|54->37|55->38|55->38|55->38|60->43|60->43|67->50|67->50|67->50|75->58|75->58|75->58
                  -- GENERATED --
              */
          