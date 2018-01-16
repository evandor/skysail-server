
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PatternsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.57*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

"""),format.raw/*10.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>Notes</h1>
        <p class="lead">all notes:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Content</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*25.14*/for(p <- rep.rawData) yield /*25.35*/ {_display_(Seq[Any](format.raw/*25.37*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.get("title")),format.raw/*27.48*/("""</th>
                <td>"""),_display_(/*28.22*/p/*28.23*/.get("content")),format.raw/*28.38*/("""</td>
                <td>
                    <a href='/demo/v1/notes/"""),_display_(/*30.46*/p/*30.47*/.get("id")),format.raw/*30.57*/("""'>[show]</a>
                    <a href='/demo/v1/notes/"""),_display_(/*31.46*/p/*31.47*/.get("id")),format.raw/*31.57*/("""/'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*34.14*/("""

            """),format.raw/*36.13*/("""</tbody>

        </table>

        <a href='/demo/v1/patterns/'>Create New Note</a>


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
                  DATE: Tue Jan 16 08:16:13 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PatternsResource_Get.scala.html
                  HASH: 3c079d3906951268256a41eacebe12850143e6ff
                  MATRIX: 717->193|867->248|895->251|917->265|956->267|985->269|1392->649|1429->670|1469->672|1510->685|1575->723|1585->724|1619->737|1673->764|1683->765|1719->780|1818->852|1828->853|1859->863|1944->921|1954->922|1985->932|2085->1001|2127->1015
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|45->30|45->30|45->30|46->31|46->31|46->31|49->34|51->36
                  -- GENERATED --
              */
          