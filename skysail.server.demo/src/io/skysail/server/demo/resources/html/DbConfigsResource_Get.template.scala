
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object DbConfigsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.28*/("""

"""),_display_(/*8.2*/main/*8.6*/ {_display_(Seq[Any](format.raw/*8.8*/("""

"""),format.raw/*10.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>DbConfigs</h1>
        <p class="lead">all keys:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>Key</th>
                <th>Values</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*26.14*/for(p <- rep.rawData) yield /*26.35*/ {_display_(Seq[Any](format.raw/*26.37*/("""
            """),format.raw/*27.13*/("""<tr>
                <th scope="row">"""),_display_(/*28.34*/p/*28.35*/.get("id")),format.raw/*28.45*/("""</th>
                <th scope="row">"""),_display_(/*29.34*/p/*29.35*/.get("key")),format.raw/*29.46*/("""</th>
                <td><a href='"""),_display_(/*30.31*/p/*30.32*/.get("values")),format.raw/*30.46*/("""'>"""),_display_(/*30.49*/p/*30.50*/.get("values")),format.raw/*30.64*/("""</a></td>
                <td><a href='"""),_display_(/*31.31*/rep/*31.34*/.linkFor("io.skysail.server.demo.resources.DbConfigsResource", p.get("id"))),format.raw/*31.109*/("""/'>[update!!]</a></td>
            </tr>
            """)))}),format.raw/*33.14*/("""

            """),format.raw/*35.13*/("""</tbody>

        </table>

        <a href='/demo/v1/dbconfigs/'>Create New Entry!</a>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Fri Dec 29 11:25:03 CET 2017
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/DbConfigsResource_Get.scala.html
                  HASH: d41a6e80316399c9c98f590c61463509b67797ba
                  MATRIX: 657->193|778->219|806->222|817->226|855->228|884->230|1319->638|1356->659|1396->661|1437->674|1502->712|1512->713|1543->723|1609->762|1619->763|1651->774|1714->810|1724->811|1759->825|1789->828|1799->829|1834->843|1901->883|1913->886|2010->961|2095->1015|2137->1029
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|40->26|40->26|40->26|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|44->30|44->30|44->30|45->31|45->31|45->31|47->33|49->35
                  -- GENERATED --
              */
          