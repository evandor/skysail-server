
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object ListResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

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

    """),format.raw/*10.5*/("""<br><br><br>

    <div class="container">
        <div class="starter-template">
            <h1>Yeah</h1>
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
                """),_display_(/*25.18*/for(p <- rep.rawData) yield /*25.39*/ {_display_(Seq[Any](format.raw/*25.41*/("""
                    """),format.raw/*26.21*/("""<tr>
                        <th scope="row">"""),_display_(/*27.42*/p/*27.43*/.get("title")),format.raw/*27.56*/("""</th>
                        <td><a href='"""),_display_(/*28.39*/p/*28.40*/.get("url")),format.raw/*28.51*/("""' target='demo_bms_"""),_display_(/*28.71*/p/*28.72*/.get("title")),format.raw/*28.85*/("""'>"""),_display_(/*28.88*/p/*28.89*/.get("url")),format.raw/*28.100*/("""</a></td>
                        <td>
                            <a href='"""),_display_(/*30.39*/rep/*30.42*/.linkFor("io.skysail.server.demo.resources.BookmarkResource", p.get("id"))),format.raw/*30.116*/("""'>[show]</a>
                            <a href='"""),_display_(/*31.39*/rep/*31.42*/.linkFor("io.skysail.server.demo.resources.PutBookmarkResource", p.get("id"))),format.raw/*31.119*/("""'>[update]</a>
                        </td>
                    </tr>
                """)))}),format.raw/*34.18*/("""

                """),format.raw/*36.17*/("""</tbody>

            </table>

            <a href='"""),_display_(/*40.23*/rep/*40.26*/.linkFor("io.skysail.server.demo.resources.PostBookmarkResource",None)),format.raw/*40.96*/("""'>Create New Bookmark</a>

            <div>
              """),_display_(/*43.16*/rep/*43.19*/.jsonData),format.raw/*43.28*/("""
            """),format.raw/*44.13*/("""</div>

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
                  DATE: Tue Jan 09 15:11:00 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: 1f7a250edcdd712425348dc7553c064257243e99
                  MATRIX: 684->198|834->253|864->258|886->272|925->274|960->282|1457->752|1494->773|1534->775|1584->797|1658->844|1668->845|1702->858|1774->903|1784->904|1816->915|1863->935|1873->936|1907->949|1937->952|1947->953|1980->964|2086->1043|2098->1046|2194->1120|2273->1172|2285->1175|2384->1252|2506->1343|2554->1363|2639->1421|2651->1424|2742->1494|2832->1557|2844->1560|2874->1569|2916->1583
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|45->30|45->30|45->30|46->31|46->31|46->31|49->34|51->36|55->40|55->40|55->40|58->43|58->43|58->43|59->44
                  -- GENERATED --
              */
          