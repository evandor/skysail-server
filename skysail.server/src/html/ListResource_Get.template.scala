
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

"""),format.raw/*10.1*/("""<br><br><br>

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

        <div>
            <h3>jsonData</h3>
            """),_display_(/*44.14*/rep/*44.17*/.jsonData),format.raw/*44.26*/("""
        """),format.raw/*45.9*/("""</div>

        <div>
            """),_display_(/*48.14*/printAppModel(rep.model)/*48.38*/ { whatever =>_display_(Seq[Any](format.raw/*48.52*/(""" """),format.raw/*48.53*/("""... """)))}),format.raw/*48.58*/("""
        """),format.raw/*49.9*/("""</div>

        <div>
            """),_display_(/*52.14*/printRequest(response.req)),format.raw/*52.40*/("""
        """),format.raw/*53.9*/("""</div>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>


"""),_display_(/*61.2*/debug("error")/*61.16*/ { color =>_display_(Seq[Any](format.raw/*61.27*/("""
  """),format.raw/*62.3*/("""Oops, something is <span style="color:"""),_display_(/*62.42*/color),format.raw/*62.47*/("""">wrong</span>
""")))}),format.raw/*63.2*/("""

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
                  DATE: Thu Jan 11 09:04:08 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: 12e2405be70852899fe932c06e37ea662292c174
                  MATRIX: 680->193|830->248|858->251|880->265|919->267|948->269|1354->648|1391->669|1431->671|1472->684|1537->722|1547->723|1581->736|1644->772|1654->773|1686->784|1733->804|1743->805|1777->818|1807->821|1817->822|1849->833|1937->894|1949->897|2045->971|2115->1014|2127->1017|2226->1094|2325->1162|2367->1176|2440->1222|2452->1225|2543->1295|2654->1379|2666->1382|2696->1391|2732->1400|2794->1435|2827->1459|2879->1473|2908->1474|2944->1479|2980->1488|3042->1523|3089->1549|3125->1558|3253->1660|3276->1674|3325->1685|3355->1688|3421->1727|3447->1732|3493->1748
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|45->30|45->30|45->30|46->31|46->31|46->31|49->34|51->36|55->40|55->40|55->40|59->44|59->44|59->44|60->45|63->48|63->48|63->48|63->48|63->48|64->49|67->52|67->52|68->53|76->61|76->61|76->61|77->62|77->62|77->62|78->63
                  -- GENERATED --
              */
          