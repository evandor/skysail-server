
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object DbConfigsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>DbConfigs</h1>
        <p class="lead">all keys:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Key</th>
                <th>Values</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*25.14*/for(p <- rep.rawData) yield /*25.35*/ {_display_(Seq[Any](format.raw/*25.37*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.get("key")),format.raw/*27.46*/("""</th>
                <td><a href='"""),_display_(/*28.31*/p/*28.32*/.get("values")),format.raw/*28.46*/("""'>"""),_display_(/*28.49*/p/*28.50*/.get("values")),format.raw/*28.64*/("""</a></td>
                <td><a href='/demo/v1/"""),_display_(/*29.40*/p/*29.41*/.get("id")),format.raw/*29.51*/("""/'>[update]</a></td>
            </tr>
            """)))}),format.raw/*31.14*/("""

            """),format.raw/*33.13*/("""</tbody>

        </table>

        <a href='/demo/v1/dbconfigs/'>Create New Entry!</a>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
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
                  DATE: Mon Mar 12 18:26:54 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/DbConfigsResource_Get.scala.html
                  HASH: 6ada411344d64707a58fee257c53341ce292f605
                  MATRIX: 725->193|886->259|914->262|936->276|975->278|1004->280|1411->660|1448->681|1488->683|1529->696|1594->734|1604->735|1636->746|1699->782|1709->783|1744->797|1774->800|1784->801|1819->815|1895->864|1905->865|1936->875|2019->927|2061->941
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|43->28|43->28|43->28|44->29|44->29|44->29|46->31|48->33
                  -- GENERATED --
              */
          