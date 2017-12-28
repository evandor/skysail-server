
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object BookmarksResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>Bookmarks</h1>
        <p class="lead">all bookmarks:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Url</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*26.14*/for(p <- rep.rawData) yield /*26.35*/ {_display_(Seq[Any](format.raw/*26.37*/("""
            """),format.raw/*27.13*/("""<tr>
                <th scope="row">"""),_display_(/*28.34*/p/*28.35*/.get("id")),format.raw/*28.45*/("""</th>
                <th scope="row">"""),_display_(/*29.34*/p/*29.35*/.get("title")),format.raw/*29.48*/("""</th>
                <td><a href='"""),_display_(/*30.31*/p/*30.32*/.get("url")),format.raw/*30.43*/("""'>"""),_display_(/*30.46*/p/*30.47*/.get("url")),format.raw/*30.58*/("""</a></td>
                <td><a href='"""),_display_(/*31.31*/rep/*31.34*/.linkFor("io.skysail.server.demo.resources.PutBookmarkResource", p.get("id"))),format.raw/*31.111*/("""'>[update!!]</a></td>
            </tr>
            """)))}),format.raw/*33.14*/("""

            """),format.raw/*35.13*/("""</tbody>

        </table>

        <a href='"""),_display_(/*39.19*/rep/*39.22*/.linkFor("io.skysail.server.demo.resources.PostBookmarkResource",None)),format.raw/*39.92*/("""'>Create New Bookmark</a>

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
                  DATE: Thu Dec 28 08:13:44 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/BookmarksResource_Get.scala.html
                  HASH: 1666ec0db66d3a17ca31dfd27d0215e561c8a4c4
                  MATRIX: 657->193|778->219|806->222|817->226|855->228|884->230|1323->642|1360->663|1400->665|1441->678|1506->716|1516->717|1547->727|1613->766|1623->767|1657->780|1720->816|1730->817|1762->828|1792->831|1802->832|1834->843|1901->883|1913->886|2012->963|2096->1016|2138->1030|2211->1076|2223->1079|2314->1149
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|40->26|40->26|40->26|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|44->30|44->30|44->30|45->31|45->31|45->31|47->33|49->35|53->39|53->39|53->39
                  -- GENERATED --
              */
          