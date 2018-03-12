
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object NotesResource_Form extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.68*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

"""),format.raw/*10.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>Notes</h1>
        <p class="lead">add note:</p>
        <form action='/demo/v1/notes/"""),_display_(/*16.39*/rep/*16.42*/.rawData.head.get("id")),format.raw/*16.65*/("""' method="post">

            <table class="table table-sm">
                <thead>
                <tr>
                    <th>Key!</th>
                    <th>Values</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row"><input type="text" name="title" value='"""),_display_(/*27.77*/rep/*27.80*/.rawData.head.get("title")),format.raw/*27.106*/("""'/></th>
                    <td><textarea name="content" size="100" rows="25">"""),_display_(/*28.72*/rep/*28.75*/.rawData.head.get("content")),format.raw/*28.103*/("""</textarea></td>
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

  def render(rep:RepresentationModel,response:ResponseEventBase,o:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,o)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,o) => apply(rep,response,o)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Mar 12 18:28:14 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/NotesResource_Form.scala.html
                  HASH: 0ae30b413a0eaed70fbaafa59e53b912d98ead85
                  MATRIX: 722->193|883->259|911->262|933->276|972->278|1001->280|1200->452|1212->455|1256->478|1627->822|1639->825|1687->851|1794->931|1806->934|1856->962
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|31->16|31->16|31->16|42->27|42->27|42->27|43->28|43->28|43->28
                  -- GENERATED --
              */
          