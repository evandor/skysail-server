
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object NotesResource_Form extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>Notes</h1>
        <p class="lead">add note:</p>
        <form action='/demo/v1/notes/"""),_display_(/*16.39*/rep/*16.42*/.rawData.head.get("id")),format.raw/*16.65*/("""' method="post">

            <table class="table table-sm">
                <thead>
                <tr>
                    <th>Key</th>
                    <th>Values</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row"><input type="text" name="title" value='"""),_display_(/*27.77*/rep/*27.80*/.rawData.head.get("title")),format.raw/*27.106*/("""'/></th>
                    <td><textarea name="content" size="100" rows="5">"""),_display_(/*28.71*/rep/*28.74*/.rawData.head.get("content")),format.raw/*28.102*/("""</textarea></td>
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
                  DATE: Mon Jan 08 09:58:33 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/NotesResource_Form.scala.html
                  HASH: 9374f1fe652d59ccb639630132bbae5a5e3a84b6
                  MATRIX: 715->193|865->248|893->251|915->265|954->267|983->269|1182->441|1194->444|1238->467|1608->810|1620->813|1668->839|1774->918|1786->921|1836->949
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|31->16|31->16|31->16|42->27|42->27|42->27|43->28|43->28|43->28
                  -- GENERATED --
              */
          