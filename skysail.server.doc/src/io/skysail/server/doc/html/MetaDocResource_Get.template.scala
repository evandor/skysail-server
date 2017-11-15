
package io.skysail.server.doc.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object MetaDocResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        yeah

        <a href='"""),_display_(/*18.19*/rep/*18.22*/.linkFor("io.skysail.server.demo.resources.PostBookmarkResource",None)),format.raw/*18.92*/("""'>Create New Bookmark</a>

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
                  DATE: Wed Nov 15 08:58:03 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.doc/./resources/templates/io/skysail/server/doc/MetaDocResource_Get.scala.html
                  HASH: f2cb44ca7713335531543c471628a85adb01bcfa
                  MATRIX: 644->193|765->219|793->222|804->226|842->228|871->230|1073->405|1085->408|1176->478
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|32->18|32->18|32->18
                  -- GENERATED --
              */
          