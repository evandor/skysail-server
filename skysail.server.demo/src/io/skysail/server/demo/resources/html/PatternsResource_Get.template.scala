
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html._

object PatternsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.57*/("""

"""),_display_(/*10.2*/main(response)/*10.16*/ {_display_(Seq[Any](format.raw/*10.18*/("""

"""),format.raw/*12.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
    
        <span>
   ***     """),_display_(/*18.13*/rep/*18.16*/.getString("[0]")),format.raw/*18.33*/("""
        """),format.raw/*19.9*/("""</span>
        <h1>Notes</h1>
        <p class="lead">all notes:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Amount</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*32.14*/for((item, i) <- rep.rawData.zipWithIndex) yield /*32.56*/ {_display_(Seq[Any](format.raw/*32.58*/("""
            """),format.raw/*33.13*/("""<tr>
                <th scope="row">"""),_display_(/*34.34*/rep/*34.37*/.getString(s"[$i].id")),format.raw/*34.59*/("""</th>
                <td>"""),_display_(/*35.22*/rep/*35.25*/.getString(s"[$i].from.title")),format.raw/*35.55*/("""</td>
                <td>"""),_display_(/*36.22*/rep/*36.25*/.getString(s"[$i].to.title")),format.raw/*36.53*/("""</td>
                <td>
                    <a href='/demo/v1/patterns/"""),_display_(/*38.49*/rep/*38.52*/.getString(s"[$i].id")),format.raw/*38.74*/("""'>[show]</a>
                    <a href='/demo/v1/patterns/"""),_display_(/*39.49*/rep/*39.52*/.getString(s"[$i].id")),format.raw/*39.74*/("""/'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*42.14*/("""

            """),format.raw/*44.13*/("""</tbody>

        </table>

        <a href='/demo/v1/patterns/'>Create New Note</a>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*52.14*/rep/*52.17*/.jsonData),format.raw/*52.26*/("""
        """),format.raw/*53.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*57.19*/rep/*57.22*/.entityModel),format.raw/*57.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*59.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*59.68*/("""</div>
        </div>

        <div>
            """),_display_(/*63.14*/printAppModel(rep.model)/*63.38*/ { whatever =>_display_(Seq[Any](format.raw/*63.52*/(""" """),format.raw/*63.53*/("""... """)))}),format.raw/*63.58*/("""
        """),format.raw/*64.9*/("""</div>

        <div>
            """),_display_(/*67.14*/printRequest(response.req)),format.raw/*67.40*/("""
        """),format.raw/*68.9*/("""</div>


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
                  DATE: Thu Jan 25 16:28:43 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PatternsResource_Get.scala.html
                  HASH: 0f2e9751749208b39970a9e45ecf324fca43aada
                  MATRIX: 187->193|551->209|701->264|730->267|753->281|793->283|822->285|954->390|966->393|1004->410|1040->419|1403->755|1461->797|1501->799|1542->812|1607->850|1619->853|1662->875|1716->902|1728->905|1779->935|1833->962|1845->965|1894->993|1996->1068|2008->1071|2051->1093|2139->1154|2151->1157|2194->1179|2294->1248|2336->1262|2506->1405|2518->1408|2548->1417|2584->1426|2687->1502|2699->1505|2732->1517|2829->1587|2899->1636|2976->1686|3009->1710|3061->1724|3090->1725|3126->1730|3162->1739|3224->1774|3271->1800|3307->1809
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|28->18|28->18|28->18|29->19|42->32|42->32|42->32|43->33|44->34|44->34|44->34|45->35|45->35|45->35|46->36|46->36|46->36|48->38|48->38|48->38|49->39|49->39|49->39|52->42|54->44|62->52|62->52|62->52|63->53|67->57|67->57|67->57|69->59|69->59|73->63|73->63|73->63|73->63|73->63|74->64|77->67|77->67|78->68
                  -- GENERATED --
              */
          