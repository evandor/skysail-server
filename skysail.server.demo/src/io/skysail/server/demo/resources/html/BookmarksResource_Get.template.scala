
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

  def render(rep:RepresentationModel,response:ResponseEventBase): play.twirl.api.HtmlFormat.Appendable = apply(rep,response)

  def f:((RepresentationModel,ResponseEventBase) => play.twirl.api.HtmlFormat.Appendable) = (rep,response) => apply(rep,response)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Jan 31 17:44:01 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarksResource_Get.scala.html
                  HASH: 846cd6a03f8247fce531346f857cd2c32dc2b37c
                  MATRIX: 718->193|868->248|896->251|918->265|957->267|986->269|1055->311|1067->314|1110->336|1139->337|1553->724|1590->745|1630->747|1671->760|1736->798|1746->799|1780->812|1843->848|1853->849|1885->860|1932->880|1942->881|1976->894|2006->897|2016->898|2048->909|2136->970|2148->973|2244->1047|2314->1090|2326->1093|2425->1170|2524->1238|2566->1252|2639->1298|2651->1301|2742->1371
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|28->13|28->13|28->13|28->13|44->29|44->29|44->29|45->30|46->31|46->31|46->31|47->32|47->32|47->32|47->32|47->32|47->32|47->32|47->32|47->32|49->34|49->34|49->34|50->35|50->35|50->35|53->38|55->40|59->44|59->44|59->44
                  -- GENERATED --
              */
          