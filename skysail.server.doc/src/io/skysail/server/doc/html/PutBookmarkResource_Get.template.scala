
package io.skysail.server.doc.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PutBookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

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
        <p class="lead">update bookmark:</p>
        <span>"""),_display_(/*16.16*/rep/*16.19*/.rawData),format.raw/*16.27*/("""</span>
        <form action='"""),_display_(/*17.24*/rep/*17.27*/.linkFor("io.skysail.app.bookmarks.PutBookmarkResource", rep.rawData.head.get("id"))),format.raw/*17.111*/("""?_method=PUT' method="GET">

            <table class="table table-sm">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Url</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row"><input type="text" name="title" value='"""),_display_(/*28.77*/rep/*28.80*/.rawData.head.get("title")),format.raw/*28.106*/("""'/></th>
                    <td><input type="url" name="url" value='"""),_display_(/*29.62*/rep/*29.65*/.rawData.head.get("url")),format.raw/*29.89*/("""'/></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit">
                    </th>
                </tr>
                </tbody>

            </table>
        </form>

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
                  DATE: Sun Jan 14 08:37:56 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.doc/./src/io/skysail/server/doc/PutBookmarkResource_Get.scala.html
                  HASH: e6f8b5d6081789b1bc74ff0c9c418d3836e98394
                  MATRIX: 709->193|859->248|887->251|909->265|948->267|977->269|1164->429|1176->432|1205->440|1263->471|1275->474|1381->558|1761->911|1773->914|1821->940|1918->1010|1930->1013|1975->1037
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|31->16|31->16|31->16|32->17|32->17|32->17|43->28|43->28|43->28|44->29|44->29|44->29
                  -- GENERATED --
              */
          