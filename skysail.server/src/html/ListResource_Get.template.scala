
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object ListResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,AnyRef,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, entity: AnyRef):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.73*/("""

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

        <a href='./'>Create New Entity</a>

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

  def render(rep:RepresentationModel,response:ResponseEventBase,entity:AnyRef): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,entity)

  def f:((RepresentationModel,ResponseEventBase,AnyRef) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,entity) => apply(rep,response,entity)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Feb 03 08:11:07 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: 1344247612ef5ce27e030fa11c2e30f0fd319c4a
                  MATRIX: 687->193|853->264|881->267|903->281|942->283|971->285|1229->516|1289->560|1329->562|1378->583|1410->588|1420->589|1472->620|1526->643|1567->656|1654->716|1691->737|1731->739|1772->752|1837->790|1847->791|1881->804|1944->840|1954->841|1986->852|2033->872|2043->873|2077->886|2107->889|2117->890|2149->901|2239->964|2249->965|2280->975|2352->1020|2362->1021|2393->1031|2493->1100|2535->1114|2691->1243|2703->1246|2733->1255|2769->1264|2872->1340|2884->1343|2917->1355|3014->1425|3084->1474|3161->1524|3194->1548|3246->1562|3275->1563|3311->1568|3347->1577|3409->1612|3456->1638|3492->1647|3620->1749|3643->1763|3692->1774|3722->1777|3788->1816|3814->1821|3860->1837
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|34->19|34->19|34->19|35->20|35->20|35->20|35->20|36->21|37->22|40->25|40->25|40->25|41->26|42->27|42->27|42->27|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|43->28|45->30|45->30|45->30|46->31|46->31|46->31|49->34|51->36|59->44|59->44|59->44|60->45|64->49|64->49|64->49|66->51|66->51|70->55|70->55|70->55|70->55|70->55|71->56|74->59|74->59|75->60|83->68|83->68|83->68|84->69|84->69|84->69|85->70
                  -- GENERATED --
              */
          