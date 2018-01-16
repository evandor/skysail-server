
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PostResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

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
        <h1>something</h1>
        <p class="lead">add bookmark:</p>
        <form action='.' method="post">

            <table class="table table-sm">
                <thead>
                <tr>
                    """),_display_(/*21.22*/for(f <- rep.entityModel.get.fields.reverse) yield /*21.66*/ {_display_(Seq[Any](format.raw/*21.68*/("""
                    """),format.raw/*22.21*/("""<th>"""),_display_(/*22.26*/f/*22.27*/.name.split("\\.").reverse.head),format.raw/*22.58*/("""</th>
                    """)))}),format.raw/*23.22*/("""
                """),format.raw/*24.17*/("""</tr>
                </thead>
                <tbody>
                <tr>
                    """),_display_(/*28.22*/for(f <- rep.entityModel.get.fields.reverse) yield /*28.66*/ {_display_(Seq[Any](format.raw/*28.68*/("""
                      """),format.raw/*29.23*/("""<td><input type="text" name='"""),_display_(/*29.53*/f/*29.54*/.name.split("\\.").reverse.head),format.raw/*29.85*/("""'/></td>
                    """)))}),format.raw/*30.22*/("""
                """),format.raw/*31.17*/("""</tr>
                <tr>
                    <th colspan="2">
                        <input type="submit">
                    </th>
                </tr>
                </tbody>

            </table>
        </form>

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
                  DATE: Tue Jan 16 08:16:12 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/PostResource_Get.scala.html
                  HASH: dc653f27b659314afed7dbeccf9698d37344de89
                  MATRIX: 680->193|830->248|858->251|880->265|919->267|948->269|1267->561|1327->605|1367->607|1416->628|1448->633|1458->634|1510->665|1568->692|1613->709|1737->806|1797->850|1837->852|1888->875|1945->905|1955->906|2007->937|2068->967|2113->984|2419->1263|2431->1266|2461->1275|2497->1284|2600->1360|2612->1363|2645->1375|2742->1445|2812->1494|2889->1544|2922->1568|2974->1582|3003->1583|3039->1588|3075->1597|3137->1632|3184->1658|3220->1667
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|36->21|36->21|36->21|37->22|37->22|37->22|37->22|38->23|39->24|43->28|43->28|43->28|44->29|44->29|44->29|44->29|45->30|46->31|59->44|59->44|59->44|60->45|64->49|64->49|64->49|66->51|66->51|70->55|70->55|70->55|70->55|70->55|71->56|74->59|74->59|75->60
                  -- GENERATED --
              */
          