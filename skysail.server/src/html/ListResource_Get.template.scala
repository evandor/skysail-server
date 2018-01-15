
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object ListResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>Yeah</h1>
        <p class="lead">all bookmarks:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                """),_display_(/*19.18*/for(f <- rep.entityModel.get.fields.reverse) yield /*19.62*/ {_display_(Seq[Any](format.raw/*19.64*/("""
                    """),format.raw/*20.21*/("""<th>"""),_display_(/*20.26*/f/*20.27*/.name.split("\\.").reverse.head),format.raw/*20.58*/("""</th>
                """)))}),format.raw/*21.18*/("""
            """),format.raw/*22.13*/("""</tr>
            </thead>
            <tbody>
            """),_display_(/*25.14*/for(p <- rep.rawData) yield /*25.35*/ {_display_(Seq[Any](format.raw/*25.37*/("""
            """),format.raw/*26.13*/("""<tr>
                <th scope="row">"""),_display_(/*27.34*/p/*27.35*/.get("title")),format.raw/*27.48*/("""</th>
                <td><a href='"""),_display_(/*28.31*/p/*28.32*/.get("url")),format.raw/*28.43*/("""' target='demo_bms_"""),_display_(/*28.63*/p/*28.64*/.get("title")),format.raw/*28.77*/("""'>"""),_display_(/*28.80*/p/*28.81*/.get("url")),format.raw/*28.92*/("""</a></td>
                <td>
                    <a href='./"""),_display_(/*30.33*/p/*30.34*/.get("id")),format.raw/*30.44*/("""'>[show]</a>
                    <a href='./"""),_display_(/*31.33*/p/*31.34*/.get("id")),format.raw/*31.44*/("""/'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*34.14*/("""

            """),format.raw/*36.13*/("""</tbody>

        </table>

        <a href='"""),_display_(/*40.19*/response/*40.27*/.req.cmd.ctx.request.uri),format.raw/*40.51*/("""/'>Create New Entity</a>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*44.14*/rep/*44.17*/.jsonData),format.raw/*44.26*/("""
        """),format.raw/*45.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*49.19*/rep/*49.22*/.entityModel),format.raw/*49.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*51.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*51.68*/("""</div>
        </div>

        <div>
            """),_display_(/*55.14*/printAppModel(rep.model)/*55.38*/ { whatever =>_display_(Seq[Any](format.raw/*55.52*/(""" """),format.raw/*55.53*/("""... """)))}),format.raw/*55.58*/("""
        """),format.raw/*56.9*/("""</div>

        <div>
            """),_display_(/*59.14*/printRequest(response.req)),format.raw/*59.40*/("""
        """),format.raw/*60.9*/("""</div>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>


"""),_display_(/*68.2*/debug("error")/*68.16*/ { color =>_display_(Seq[Any](format.raw/*68.27*/("""
  """),format.raw/*69.3*/("""Oops, something is <span style="color:"""),_display_(/*69.42*/color),format.raw/*69.47*/("""">wrong</span>
""")))}),format.raw/*70.2*/("""

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
                  DATE: Mon Jan 15 09:09:55 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: 29c1ae4ea3ad70dba553756741ed5321a4d1537e
                  MATRIX: 680->193|830->248|858->251|880->265|919->267|948->269|1206->500|1266->544|1306->546|1355->567|1387->572|1397->573|1449->604|1503->627|1544->640|1631->700|1668->721|1708->723|1749->736|1814->774|1824->775|1858->788|1921->824|1931->825|1963->836|2010->856|2020->857|2054->870|2084->873|2094->874|2126->885|2216->948|2226->949|2257->959|2329->1004|2339->1005|2370->1015|2470->1084|2512->1098|2585->1144|2602->1152|2647->1176|2757->1259|2769->1262|2799->1271|2835->1280|2938->1356|2950->1359|2983->1371|3080->1441|3150->1490|3227->1540|3260->1564|3312->1578|3341->1579|3377->1584|3413->1593|3475->1628|3522->1654|3558->1663|3686->1765|3709->1779|3758->1790|3788->1793|3854->1832|3880->1837|3926->1853
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|34->19|34->19|34->19|35->20|35->20|35->20|35->20|36->21|37->22|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|45->30|45->30|45->30|46->31|46->31|46->31|49->34|51->36|55->40|55->40|55->40|59->44|59->44|59->44|60->45|64->49|64->49|64->49|66->51|66->51|70->55|70->55|70->55|70->55|70->55|71->56|74->59|74->59|75->60|83->68|83->68|83->68|84->69|84->69|84->69|85->70
                  -- GENERATED --
              */
          