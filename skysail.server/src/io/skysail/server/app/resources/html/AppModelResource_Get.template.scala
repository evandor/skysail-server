
package io.skysail.server.app.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object AppModelResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.app.AppModelDescription,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(rep: RepresentationModel, response: ResponseEventBase, m: io.skysail.server.app.AppModelDescription):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.103*/("""

"""),_display_(/*3.2*/main(response)/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""

"""),format.raw/*5.1*/("""<div class="container">
    <div class="starter-template">
        <h1>Application Model</h1>
        <p class="lead">"""),_display_(/*8.26*/m/*8.27*/.name),format.raw/*8.32*/(""" """),format.raw/*8.33*/("""["""),_display_(/*8.35*/m/*8.36*/.version),format.raw/*8.44*/("""]</p>
        <p>"""),_display_(/*9.13*/m/*9.14*/.description),format.raw/*9.26*/("""</p>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Url (Pattern)</th>
                <th scope="col">Resource</th>
                <th scope="col">Associated Entity</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*20.14*/for(r <- m.resources) yield /*20.35*/ {_display_(Seq[Any](format.raw/*20.37*/("""
            """),format.raw/*21.13*/("""<tr>
                <td>
                    """),_display_(/*23.22*/if(r.path.contains(":"))/*23.46*/ {_display_(Seq[Any](format.raw/*23.48*/("""
                      """),_display_(/*24.24*/r/*24.25*/.path),format.raw/*24.30*/("""
                    """)))}/*25.23*/else/*25.28*/{_display_(Seq[Any](format.raw/*25.29*/("""
                      """),format.raw/*26.23*/("""<a href="/"""),_display_(/*26.34*/m/*26.35*/.name),format.raw/*26.40*/("""/"""),_display_(/*26.42*/{m.version}),_display_(/*26.54*/r/*26.55*/.path),format.raw/*26.60*/(""""> """),_display_(/*26.64*/r/*26.65*/.path),format.raw/*26.70*/(""" """),format.raw/*26.71*/("""</a>
                    """)))}),format.raw/*27.22*/("""
                """),format.raw/*28.17*/("""</td>
                <td><a href="_model/resources/"""),_display_(/*29.48*/r/*29.49*/.resClass),format.raw/*29.58*/("""">"""),_display_(/*29.61*/r/*29.62*/.resClass),format.raw/*29.71*/("""</a></td>
                <td><a href="_model/entities/"""),_display_(/*30.47*/r/*30.48*/.entityType),format.raw/*30.59*/("""">"""),_display_(/*30.62*/r/*30.63*/.entityType),format.raw/*30.74*/("""</a></td>
            </tr>
            """)))}),format.raw/*32.14*/("""
            """),format.raw/*33.13*/("""</tbody>
        </table>


    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,m:io.skysail.server.app.AppModelDescription): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,m)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.server.app.AppModelDescription) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,m) => apply(rep,response,m)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Mar 20 07:52:06 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/io/skysail/server/app/resources/AppModelResource_Get.scala.html
                  HASH: eee13307824a5189064959da79126b5e16c63939
                  MATRIX: 571->1|768->102|796->105|818->119|857->121|885->123|1030->242|1039->243|1064->248|1092->249|1120->251|1129->252|1157->260|1201->278|1210->279|1242->291|1566->588|1603->609|1643->611|1684->624|1758->671|1791->695|1831->697|1882->721|1892->722|1918->727|1959->750|1972->755|2011->756|2062->779|2100->790|2110->791|2136->796|2165->798|2197->810|2207->811|2233->816|2264->820|2274->821|2300->826|2329->827|2386->853|2431->870|2511->923|2521->924|2551->933|2581->936|2591->937|2621->946|2704->1002|2714->1003|2746->1014|2776->1017|2786->1018|2818->1029|2890->1070|2931->1083
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|24->8|24->8|24->8|24->8|24->8|24->8|24->8|25->9|25->9|25->9|36->20|36->20|36->20|37->21|39->23|39->23|39->23|40->24|40->24|40->24|41->25|41->25|41->25|42->26|42->26|42->26|42->26|42->26|42->26|42->26|42->26|42->26|42->26|42->26|42->26|43->27|44->28|45->29|45->29|45->29|45->29|45->29|45->29|46->30|46->30|46->30|46->30|46->30|46->30|48->32|49->33
                  -- GENERATED --
              */
          