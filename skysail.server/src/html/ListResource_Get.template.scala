
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

        <div>
            <h3>links</h3>
            """),_display_(/*35.14*/Html(rep.response.entity.asInstanceOf[io.skysail.api.ui.Linkable]._links.filter(_.rel == "create-form").headOption.map(_.toHtml()).getOrElse(""))),format.raw/*35.159*/("""
        """),format.raw/*36.9*/("""</div>

        <div>
            <h3>rawData</h3>
            """),_display_(/*40.14*/rep/*40.17*/.rawData),format.raw/*40.25*/("""
        """),format.raw/*41.9*/("""</div>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*45.14*/rep/*45.17*/.jsonData),format.raw/*45.26*/("""
        """),format.raw/*46.9*/("""</div>

        <div>
            <h3>rep.response.entity.asInstanceOf[io.skysail.api.ui.Linkable]</h3>
            """),_display_(/*50.14*/rep/*50.17*/.response.entity.asInstanceOf[io.skysail.api.ui.Linkable]._links),format.raw/*50.81*/("""
        """),format.raw/*51.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*55.19*/rep/*55.22*/.entityModel),format.raw/*55.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*57.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*57.68*/("""</div>
        </div>

        <div>
            """),_display_(/*61.14*/printAppModel(rep.model)/*61.38*/ { whatever =>_display_(Seq[Any](format.raw/*61.52*/(""" """),format.raw/*61.53*/("""... """)))}),format.raw/*61.58*/("""
        """),format.raw/*62.9*/("""</div>

        <div>
            """),_display_(/*65.14*/printRequest(response.req)),format.raw/*65.40*/("""
        """),format.raw/*66.9*/("""</div>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>


"""),_display_(/*74.2*/debug("error")/*74.16*/ { color =>_display_(Seq[Any](format.raw/*74.27*/("""
  """),format.raw/*75.3*/("""<!-- output from debug -->
""")))}),format.raw/*76.2*/("""

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
                  DATE: Mon Mar 12 18:07:05 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: a86921e28878d53116003b28984231d007e0cccb
                  MATRIX: 500->1|666->72|694->75|716->89|755->91|783->93|881->165|892->168|923->179|1091->320|1151->364|1191->366|1240->387|1272->392|1282->393|1334->424|1388->447|1429->460|1516->520|1553->541|1593->543|1634->556|1681->576|1741->620|1781->622|1826->639|1858->644|1868->645|1927->683|1979->704|2024->721|2086->756|2141->790|2170->792|2204->805|2300->870|2342->884|2451->966|2618->1111|2654->1120|2745->1184|2757->1187|2786->1195|2822->1204|2914->1269|2926->1272|2956->1281|2992->1290|3136->1407|3148->1410|3233->1474|3269->1483|3372->1559|3384->1562|3417->1574|3514->1644|3584->1693|3661->1743|3694->1767|3746->1781|3775->1782|3811->1787|3847->1796|3909->1831|3956->1857|3992->1866|4120->1968|4143->1982|4192->1993|4222->1996|4280->2024
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|23->7|23->7|23->7|28->12|28->12|28->12|29->13|29->13|29->13|29->13|30->14|31->15|34->18|34->18|34->18|35->19|36->20|36->20|36->20|37->21|37->21|37->21|37->21|38->22|39->23|40->24|40->24|40->24|40->24|43->27|45->29|51->35|51->35|52->36|56->40|56->40|56->40|57->41|61->45|61->45|61->45|62->46|66->50|66->50|66->50|67->51|71->55|71->55|71->55|73->57|73->57|77->61|77->61|77->61|77->61|77->61|78->62|81->65|81->65|82->66|90->74|90->74|90->74|91->75|92->76
                  -- GENERATED --
              */
          