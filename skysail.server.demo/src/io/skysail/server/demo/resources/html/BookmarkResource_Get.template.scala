
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object BookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <p class="lead">show bookmark:</p>
        <span>"""),_display_(/*16.16*/rep/*16.19*/.rawData),format.raw/*16.27*/("""</span>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Title</th>
                <th>Url</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><input type="text" name="title" value='"""),_display_(/*26.73*/rep/*26.76*/.rawData.head.get("title")),format.raw/*26.102*/("""'/></th>
                <td><input type="url" name="url" value='"""),_display_(/*27.58*/rep/*27.61*/.rawData.head.get("url")),format.raw/*27.85*/("""'/></td>
            </tr>
            <tr>
                <th colspan="2">
                    <a href='"""),_display_(/*31.31*/rep/*31.34*/.rawData.head.get("id")),format.raw/*31.57*/("""/'>edit bookmark</a>
                </th>
            </tr>
            </tbody>

        </table>

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
                  DATE: Tue Nov 28 17:50:37 CET 2017
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./resources/templates/io/skysail/server/demo/resources/BookmarkResource_Get.scala.html
                  HASH: 3bb5755fe2d8221803ab4f55abe4f00c781366e9
                  MATRIX: 656->193|777->219|805->222|816->226|854->228|883->230|1068->388|1080->391|1109->399|1428->691|1440->694|1488->720|1581->786|1593->789|1638->813|1772->920|1784->923|1828->946
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|30->16|30->16|30->16|40->26|40->26|40->26|41->27|41->27|41->27|45->31|45->31|45->31
                  -- GENERATED --
              */
          