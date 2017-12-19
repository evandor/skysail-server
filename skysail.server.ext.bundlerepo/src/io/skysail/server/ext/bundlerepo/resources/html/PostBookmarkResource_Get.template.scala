
package io.skysail.server.ext.bundlerepo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object PostBookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <p class="lead">add bookmark:</p>
        <form action='"""),_display_(/*16.24*/rep/*16.27*/.linkFor("io.skysail.app.bookmarks.PostBookmarkResource", None)),format.raw/*16.90*/("""' method="post">

            <table class="table table-sm">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Url</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row"><input type="text" name="title"/></th>
                    <td><input type="url" name="url"/></td>
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
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.ext.bundlerepo/./resources/templates/io/skysail/server/ext/bundlerepo/resources/PostBookmarkResource_Get.scala.html
                  HASH: f4d7c35d668ac555f4a3a51a711cb5ae2ee85a48
                  MATRIX: 670->193|791->219|819->222|830->226|868->228|897->230|1089->395|1101->398|1185->461
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|30->16|30->16|30->16
                  -- GENERATED --
              */
          