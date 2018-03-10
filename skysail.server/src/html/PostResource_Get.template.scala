
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

  def render(rep:RepresentationModel,response:ResponseEventBase,o:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,o)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,o) => apply(rep,response,o)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Fri Mar 09 17:47:50 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/PostResource_Get.scala.html
                  HASH: 43ba49a0d44293a4df1a28990e0c89377b424e37
                  MATRIX: 687->193|847->258|875->261|897->275|936->277|965->279|1284->571|1344->615|1384->617|1433->638|1465->643|1475->644|1527->675|1585->702|1630->719|1754->816|1814->860|1854->862|1905->885|1962->915|1972->916|2024->947|2085->977|2130->994|2436->1273|2448->1276|2478->1285|2514->1294|2617->1370|2629->1373|2662->1385|2759->1455|2829->1504|2906->1554|2939->1578|2991->1592|3020->1593|3056->1598|3092->1607|3154->1642|3201->1668|3237->1677
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|36->21|36->21|36->21|37->22|37->22|37->22|37->22|38->23|39->24|43->28|43->28|43->28|44->29|44->29|44->29|44->29|45->30|46->31|59->44|59->44|59->44|60->45|64->49|64->49|64->49|66->51|66->51|70->55|70->55|70->55|70->55|70->55|71->56|74->59|74->59|75->60
                  -- GENERATED --
              */
          