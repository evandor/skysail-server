
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object DbConfigsResource_Form extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

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
        <p class="lead">add bookmark:</p>
        <form action='/demo/v1/dbconfigs/' method="post">

            <table class="table table-sm">
                <thead>
                <tr>
                    <th>Key</th>
                    <th>Values</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row"><input type="text" name="key"/></th>
                    <td><input type="text" name="values" size="100"/></td>
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
                  DATE: Mon Jan 08 09:58:34 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/DbConfigsResource_Form.scala.html
                  HASH: fef31f19dfbf459b7636608847d807b709467bee
                  MATRIX: 719->193|869->248|897->251|919->265|958->267|987->269
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10
                  -- GENERATED --
              */
          