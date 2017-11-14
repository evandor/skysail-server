
package io.skysail.server.demo.html

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
                <td><a href='"""),_display_(/*31.31*/rep/*31.34*/.linkFor("io.skysail.app.bookmarks.PutBookmarkResource", p.get("id"))),format.raw/*31.103*/("""'>[update]</a></td>
            </tr>
            """)))}),format.raw/*33.14*/("""

            """),format.raw/*35.13*/("""</tbody>

        </table>

        <a href="/bookmarks/v1/bm/">Create New Bookmark</a>

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
                  DATE: Tue Nov 14 16:30:36 CET 2017
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./resources/templates/io/skysail/server/demo/BookmarksResource_Get.scala.html
                  HASH: b3db0ca82de27ee58b3998e77106ce6615c44ad4
                  MATRIX: 647->193|768->219|796->222|807->226|845->228|874->230|1313->642|1350->663|1390->665|1431->678|1496->716|1506->717|1537->727|1603->766|1613->767|1647->780|1710->816|1720->817|1752->828|1782->831|1792->832|1824->843|1891->883|1903->886|1994->955|2076->1006|2118->1020
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|40->26|40->26|40->26|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|44->30|44->30|44->30|45->31|45->31|45->31|47->33|49->35
                  -- GENERATED --
              */
          