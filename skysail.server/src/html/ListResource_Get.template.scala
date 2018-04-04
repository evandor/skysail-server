
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object ListResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,AnyRef,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(rep: RepresentationModel, response: ResponseEventBase, entity: AnyRef):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.73*/("""

"""),_display_(/*3.2*/main(response)/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""

"""),format.raw/*5.1*/("""<div class="container">
    <div class="starter-template">
        <h1>"""),_display_(/*7.14*/rep/*7.17*/.model.name),format.raw/*7.28*/("""</h1>
        <p class="lead">all entities:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                """),_display_(/*12.18*/for(f <- rep.entityModel.get.fields.reverse) yield /*12.62*/ {_display_(Seq[Any](format.raw/*12.64*/("""
                    """),format.raw/*13.21*/("""<th>"""),_display_(/*13.26*/f/*13.27*/.name.split("\\.").reverse.head),format.raw/*13.58*/("""</th>
                """)))}),format.raw/*14.18*/("""
            """),format.raw/*15.13*/("""</tr>
            </thead>
            <tbody>
            """),_display_(/*18.14*/for(p <- rep.rawData) yield /*18.35*/ {_display_(Seq[Any](format.raw/*18.37*/("""
            """),format.raw/*19.13*/("""<tr>
              """),_display_(/*20.16*/for(f <- rep.entityModel.get.fields.reverse) yield /*20.60*/ {_display_(Seq[Any](format.raw/*20.62*/("""
                """),format.raw/*21.17*/("""<td>"""),_display_(/*21.22*/p/*21.23*/.get(f.name.split("\\.").reverse.head)),format.raw/*21.61*/("""</td>
              """)))}),format.raw/*22.16*/("""
                """),format.raw/*23.17*/("""<td>
                    <a href='"""),_display_(/*24.31*/{response.req.cmd.ctx.request.uri}),format.raw/*24.65*/("""/"""),_display_(/*24.67*/{p.get("id")}),format.raw/*24.80*/("""/'>Edit</a>
                </td>
            </tr>
            """)))}),format.raw/*27.14*/("""

            """),format.raw/*29.13*/("""</tbody>

        </table>

        <a href=""""),_display_(/*33.19*/{response.req.cmd.ctx.request.uri}),format.raw/*33.53*/("""/">new entity!</a>

        <div>
            <h3>links</h3>
            """),_display_(/*37.14*/Html(rep.links.filter(_.rel == "create-form").headOption.map(_.toHtml()).getOrElse(""))),format.raw/*37.101*/("""
        """),format.raw/*38.9*/("""</div>

        <!--<div>
            <h3>rawData</h3>
            """),_display_(/*42.14*/rep/*42.17*/.rawData),format.raw/*42.25*/("""
        """),format.raw/*43.9*/("""</div>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*47.14*/rep/*47.17*/.jsonData),format.raw/*47.26*/("""
        """),format.raw/*48.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*52.19*/rep/*52.22*/.entityModel),format.raw/*52.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*54.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*54.68*/("""</div>
        </div>

        <div>
            """),_display_(/*58.14*/printAppModel(rep.model)/*58.38*/ { whatever =>_display_(Seq[Any](format.raw/*58.52*/(""" """),format.raw/*58.53*/("""... """)))}),format.raw/*58.58*/("""
        """),format.raw/*59.9*/("""</div>

        <div>
            """),_display_(/*62.14*/printRequest(response.req)),format.raw/*62.40*/("""
        """),format.raw/*63.9*/("""</div>-->

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>


"""),_display_(/*71.2*/debug("error")/*71.16*/ { color =>_display_(Seq[Any](format.raw/*71.27*/("""
  """),format.raw/*72.3*/("""<!-- output from debug -->
""")))}),format.raw/*73.2*/("""

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
                  DATE: Tue Apr 03 20:22:14 CEST 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: f353d0b4de473d2977e2672f97308876c7f75c5d
                  MATRIX: 500->1|666->72|694->75|716->89|755->91|783->93|881->165|892->168|923->179|1091->320|1151->364|1191->366|1240->387|1272->392|1282->393|1334->424|1388->447|1429->460|1516->520|1553->541|1593->543|1634->556|1681->576|1741->620|1781->622|1826->639|1858->644|1868->645|1927->683|1979->704|2024->721|2086->756|2141->790|2170->792|2204->805|2300->870|2342->884|2415->930|2470->964|2571->1038|2680->1125|2716->1134|2811->1202|2823->1205|2852->1213|2888->1222|2980->1287|2992->1290|3022->1299|3058->1308|3161->1384|3173->1387|3206->1399|3303->1469|3373->1518|3450->1568|3483->1592|3535->1606|3564->1607|3600->1612|3636->1621|3698->1656|3745->1682|3781->1691|3912->1796|3935->1810|3984->1821|4014->1824|4072->1852
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|23->7|23->7|23->7|28->12|28->12|28->12|29->13|29->13|29->13|29->13|30->14|31->15|34->18|34->18|34->18|35->19|36->20|36->20|36->20|37->21|37->21|37->21|37->21|38->22|39->23|40->24|40->24|40->24|40->24|43->27|45->29|49->33|49->33|53->37|53->37|54->38|58->42|58->42|58->42|59->43|63->47|63->47|63->47|64->48|68->52|68->52|68->52|70->54|70->54|74->58|74->58|74->58|74->58|74->58|75->59|78->62|78->62|79->63|87->71|87->71|87->71|88->72|89->73
                  -- GENERATED --
              */
          