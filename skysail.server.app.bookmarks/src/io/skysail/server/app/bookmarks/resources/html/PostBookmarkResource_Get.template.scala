
package io.skysail.server.app.bookmarks.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PostBookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, e:Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.67*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

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

  def render(rep:RepresentationModel,response:ResponseEventBase,e:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,e)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,e) => apply(rep,response,e)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Feb 18 12:38:14 CET 2018
                  SOURCE: /Users/carsten/git/skysail-apps/skysail.server.app.bookmarks/./src/io/skysail/server/app/bookmarks/resources/PostBookmarkResource_Get.scala.html
                  HASH: be9e0fd702c361479e49e1c58ce0519db6c8f464
                  MATRIX: 737->193|897->258|925->261|947->275|986->277|1015->279|1207->444|1219->447|1303->510
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|31->16|31->16|31->16
                  -- GENERATED --
              */
          