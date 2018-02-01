
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

    <span>
        ***     """),_display_(/*13.18*/rep/*13.21*/.itemsOf(".bookmarks")),format.raw/*13.43*/(""" """),format.raw/*13.44*/("""***
    </span>

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
            """),_display_(/*29.14*/for(p <- rep.rawData) yield /*29.35*/ {_display_(Seq[Any](format.raw/*29.37*/("""
            """),format.raw/*30.13*/("""<tr>
                <th scope="row">"""),_display_(/*31.34*/p/*31.35*/.get("title")),format.raw/*31.48*/("""</th>
                <td><a href='"""),_display_(/*32.31*/p/*32.32*/.get("url")),format.raw/*32.43*/("""' target='demo_bms_"""),_display_(/*32.63*/p/*32.64*/.get("title")),format.raw/*32.77*/("""'>"""),_display_(/*32.80*/p/*32.81*/.get("url")),format.raw/*32.92*/("""</a></td>
                <td>
                    <a href='"""),_display_(/*34.31*/rep/*34.34*/.linkFor("io.skysail.server.demo.resources.BookmarkResource", p.get("id"))),format.raw/*34.108*/("""'>[show]</a>
                    <a href='"""),_display_(/*35.31*/rep/*35.34*/.linkFor("io.skysail.server.demo.resources.PutBookmarkResource", p.get("id"))),format.raw/*35.111*/("""'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*38.14*/("""

            """),format.raw/*40.13*/("""</tbody>

        </table>

        <a href='"""),_display_(/*44.19*/rep/*44.22*/.linkFor("io.skysail.server.demo.resources.PostBookmarkResource",None)),format.raw/*44.92*/("""'>Create New Bookmark</a>

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
                  DATE: Thu Feb 01 09:40:43 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarksResource_Get.scala.html
                  HASH: cc95e35ecd134f95dac9447452ceba8658639443
                  MATRIX: 761->193|961->297|989->300|1011->314|1050->316|1079->318|1148->360|1160->363|1203->385|1232->386|1646->773|1683->794|1723->796|1764->809|1829->847|1839->848|1873->861|1936->897|1946->898|1978->909|2025->929|2035->930|2069->943|2099->946|2109->947|2141->958|2229->1019|2241->1022|2337->1096|2407->1139|2419->1142|2518->1219|2617->1287|2659->1301|2732->1347|2744->1350|2835->1420
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|28->13|28->13|28->13|28->13|44->29|44->29|44->29|45->30|46->31|46->31|46->31|47->32|47->32|47->32|47->32|47->32|47->32|47->32|47->32|47->32|49->34|49->34|49->34|50->35|50->35|50->35|53->38|55->40|59->44|59->44|59->44
                  -- GENERATED --
              */
          