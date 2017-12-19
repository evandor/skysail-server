
package io.skysail.server.ext.bundlerepo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object PutBookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <p class="lead">update bookmark:</p>
        <span>"""),_display_(/*16.16*/rep/*16.19*/.rawData),format.raw/*16.27*/("""</span>
        <form action='.?_method=PUT' method="POST">

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

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Dec 19 08:33:41 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.ext.bundlerepo/./resources/templates/io/skysail/server/ext/bundlerepo/resources/PutBookmarkResource_Get.scala.html
                  HASH: 2f4a8bf3fc61e1b0db8f798ed6a3f344e6fd6937
                  MATRIX: 669->193|790->219|818->222|829->226|867->228|896->230|1083->390|1095->393|1124->401|1536->786|1548->789|1596->815|1693->885|1705->888|1750->912
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|30->16|30->16|30->16|42->28|42->28|42->28|43->29|43->29|43->29
                  -- GENERATED --
              */
          