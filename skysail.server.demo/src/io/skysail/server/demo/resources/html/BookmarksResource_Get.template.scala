
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object BookmarksResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.demo.domain.BookmarkList,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, bms: io.skysail.server.demo.domain.BookmarkList):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.106*/("""

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
            """),_display_(/*25.14*/for(p <- bms.bookmarks) yield /*25.37*/ {_display_(Seq[Any](format.raw/*25.39*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.title),format.raw/*27.41*/("""</th>
                <td><a href='"""),_display_(/*28.31*/p/*28.32*/.url),format.raw/*28.36*/("""' target='demo_bms_"""),_display_(/*28.56*/p/*28.57*/.title),format.raw/*28.63*/(""")'>"""),_display_(/*28.67*/p/*28.68*/.url),format.raw/*28.72*/("""</a></td>
                <td>
                    <a href='"""),_display_(/*30.31*/rep/*30.34*/.linkFor("io.skysail.server.demo.resources.BookmarkResource", p.id)),format.raw/*30.101*/("""'>[show]</a>
                    <a href='"""),_display_(/*31.31*/rep/*31.34*/.linkFor("io.skysail.server.demo.resources.PutBookmarkResource", p.id)),format.raw/*31.104*/("""'>[update]</a>
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

  def render(rep:RepresentationModel,response:ResponseEventBase,bms:io.skysail.server.demo.domain.BookmarkList): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,bms)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.server.demo.domain.BookmarkList) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,bms) => apply(rep,response,bms)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Fri Feb 02 18:00:08 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarksResource_Get.scala.html
                  HASH: 48ebc02a548da142dbb4fff3f4c8b303e6324292
                  MATRIX: 761->193|961->297|989->300|1011->314|1050->316|1079->318|1490->702|1529->725|1569->727|1610->740|1675->778|1685->779|1712->785|1775->821|1785->822|1810->826|1857->846|1867->847|1894->853|1925->857|1935->858|1960->862|2048->923|2060->926|2149->993|2219->1036|2231->1039|2323->1109|2422->1177|2464->1191|2537->1237|2549->1240|2640->1310
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|45->30|45->30|45->30|46->31|46->31|46->31|49->34|51->36|55->40|55->40|55->40
                  -- GENERATED --
              */
          