
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PostResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, o:Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.67*/("""

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


       <!-- <div>
            <h3>jsonData</h3>
            """),_display_(/*45.14*/rep/*45.17*/.jsonData),format.raw/*45.26*/("""
        """),format.raw/*46.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*50.19*/rep/*50.22*/.entityModel),format.raw/*50.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*52.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*52.68*/("""</div>
        </div>

        <div>
            """),_display_(/*56.14*/printAppModel(rep.model)/*56.38*/ { whatever =>_display_(Seq[Any](format.raw/*56.52*/(""" """),format.raw/*56.53*/("""... """)))}),format.raw/*56.58*/("""
        """),format.raw/*57.9*/("""</div>

        <div>
            """),_display_(/*60.14*/printRequest(response.req)),format.raw/*60.40*/("""
        """),format.raw/*61.9*/("""</div>-->


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
                  DATE: Sat Mar 17 09:48:22 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/PostResource_Get.scala.html
                  HASH: 8e00283b9814507adf63c772d0c2c5dd4c1845c2
                  MATRIX: 687->193|847->258|875->261|897->275|936->277|965->279|1284->571|1344->615|1384->617|1433->638|1465->643|1475->644|1527->675|1585->702|1630->719|1754->816|1814->860|1854->862|1905->885|1962->915|1972->916|2024->947|2085->977|2130->994|2441->1278|2453->1281|2483->1290|2519->1299|2622->1375|2634->1378|2667->1390|2764->1460|2834->1509|2911->1559|2944->1583|2996->1597|3025->1598|3061->1603|3097->1612|3159->1647|3206->1673|3242->1682
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|36->21|36->21|36->21|37->22|37->22|37->22|37->22|38->23|39->24|43->28|43->28|43->28|44->29|44->29|44->29|44->29|45->30|46->31|60->45|60->45|60->45|61->46|65->50|65->50|65->50|67->52|67->52|71->56|71->56|71->56|71->56|71->56|72->57|75->60|75->60|76->61
                  -- GENERATED --
              */
          