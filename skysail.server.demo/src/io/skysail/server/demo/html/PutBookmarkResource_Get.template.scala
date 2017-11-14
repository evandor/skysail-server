
package io.skysail.server.demo.html

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

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Nov 14 16:30:36 CET 2017
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./resources/templates/io/skysail/server/demo/PutBookmarkResource_Get.scala.html
                  HASH: 80a92cb4e723bd05204b2ca4b550062fa4e4e453
                  MATRIX: 649->193|770->219|798->222|809->226|847->228|876->230|1063->390|1075->393|1104->401|1162->432|1174->435|1280->519|1660->872|1672->875|1720->901|1817->971|1829->974|1874->998
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|30->16|30->16|30->16|31->17|31->17|31->17|42->28|42->28|42->28|43->29|43->29|43->29
                  -- GENERATED --
              */
          