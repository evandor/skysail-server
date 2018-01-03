
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object NotesResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>Notes</h1>
        <p class="lead">all notes:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Content</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*25.14*/for(p <- rep.rawData) yield /*25.35*/ {_display_(Seq[Any](format.raw/*25.37*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.get("title")),format.raw/*27.48*/("""</th>
                <td>"""),_display_(/*28.22*/p/*28.23*/.get("content")),format.raw/*28.38*/("""</td>
                <td>
                    <a href='/demo/v1/notes/"""),_display_(/*30.46*/p/*30.47*/.get("id")),format.raw/*30.57*/("""'>[show]</a>
                    <a href='/demo/v1/notes/"""),_display_(/*31.46*/p/*31.47*/.get("id")),format.raw/*31.57*/("""/'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*34.14*/("""

            """),format.raw/*36.13*/("""</tbody>

        </table>

        <a href='/demo/v1/notes/'>Create New Note</a>

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
                  DATE: Wed Jan 03 16:19:48 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/NotesResource_Get.scala.html
                  HASH: 35f6c5844121658ab0a23cfe0d25ebfc6f31b730
                  MATRIX: 653->193|774->219|802->222|813->226|851->228|880->230|1287->610|1324->631|1364->633|1405->646|1470->684|1480->685|1514->698|1568->725|1578->726|1614->741|1713->813|1723->814|1754->824|1839->882|1849->883|1880->893|1980->962|2022->976
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|39->25|39->25|39->25|40->26|41->27|41->27|41->27|42->28|42->28|42->28|44->30|44->30|44->30|45->31|45->31|45->31|48->34|50->36
                  -- GENERATED --
              */
          