
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object BookmarksResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>Bookmarks</h1>
        <p class="lead">all bookmarks:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Url</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*25.14*/for(p <- rep.rawData) yield /*25.35*/ {_display_(Seq[Any](format.raw/*25.37*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.get("title")),format.raw/*27.48*/("""</th>
                <td><a href='"""),_display_(/*28.31*/p/*28.32*/.get("url")),format.raw/*28.43*/("""' target='demo_bms_"""),_display_(/*28.63*/p/*28.64*/.get("title")),format.raw/*28.77*/("""'>"""),_display_(/*28.80*/p/*28.81*/.get("url")),format.raw/*28.92*/("""</a></td>
                <td>
                    <a href='"""),_display_(/*30.31*/rep/*30.34*/.linkFor("io.skysail.server.demo.resources.BookmarkResource", p.get("id"))),format.raw/*30.108*/("""'>[show]</a>
                    <a href='"""),_display_(/*31.31*/rep/*31.34*/.linkFor("io.skysail.server.demo.resources.PutBookmarkResource", p.get("id"))),format.raw/*31.111*/("""'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*34.14*/("""

            """),format.raw/*36.13*/("""</tbody>

        </table>

        <a href='"""),_display_(/*40.19*/rep/*40.22*/.linkFor("io.skysail.server.demo.resources.PostBookmarkResource",None)),format.raw/*40.92*/("""'>Create New Bookmark</a>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
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
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarksResource_Get.scala.html
                  HASH: 3ebeafda1ef1eaee676b0c5a04084e5f4c62f322
                  MATRIX: 718->193|868->248|896->251|918->265|957->267|986->269|1397->653|1434->674|1474->676|1515->689|1580->727|1590->728|1624->741|1687->777|1697->778|1729->789|1776->809|1786->810|1820->823|1850->826|1860->827|1892->838|1980->899|1992->902|2088->976|2158->1019|2170->1022|2269->1099|2368->1167|2410->1181|2483->1227|2495->1230|2586->1300
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|45->30|45->30|45->30|46->31|46->31|46->31|49->34|51->36|55->40|55->40|55->40
                  -- GENERATED --
              */
          