
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
        <p class="lead">all bookmarks:</p>
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
                <th scope="row">"""),_display_(/*20.34*/p/*20.35*/.get("title")),format.raw/*20.48*/("""</th>
                <td><a href='"""),_display_(/*21.31*/p/*21.32*/.get("url")),format.raw/*21.43*/("""' target='demo_bms_"""),_display_(/*21.63*/p/*21.64*/.get("title")),format.raw/*21.77*/("""'>"""),_display_(/*21.80*/p/*21.81*/.get("url")),format.raw/*21.92*/("""</a></td>
                <td>
                    <a href='./"""),_display_(/*23.33*/p/*23.34*/.get("id")),format.raw/*23.44*/("""'>[show]</a>
                    <a href='./"""),_display_(/*24.33*/p/*24.34*/.get("id")),format.raw/*24.44*/("""/'>[update]</a>
                </td>
            </tr>
            """)))}),format.raw/*27.14*/("""

            """),format.raw/*29.13*/("""</tbody>

        </table>

        <a href='./'>Create New Entity</a>

        <div>
            <h3>jsonData</h3>
            """),_display_(/*37.14*/rep/*37.17*/.jsonData),format.raw/*37.26*/("""
        """),format.raw/*38.9*/("""</div>

        <div>
            <h3>current Entity</h3>
            <div>"""),_display_(/*42.19*/rep/*42.22*/.entityModel),format.raw/*42.34*/("""</div>
            <h3>current Entity's fields</h3>
            <div>"""),_display_(/*44.19*/Html(rep.entityModel.get.fields.mkString("<br>"))),format.raw/*44.68*/("""</div>
        </div>

        <div>
            """),_display_(/*48.14*/printAppModel(rep.model)/*48.38*/ { whatever =>_display_(Seq[Any](format.raw/*48.52*/(""" """),format.raw/*48.53*/("""... """)))}),format.raw/*48.58*/("""
        """),format.raw/*49.9*/("""</div>

        <div>
            """),_display_(/*52.14*/printRequest(response.req)),format.raw/*52.40*/("""
        """),format.raw/*53.9*/("""</div>

        <hr>
        <a href="/doc/v1/index.html" target="_docs">Doc</a>
    </div>
</div>


"""),_display_(/*61.2*/debug("error")/*61.16*/ { color =>_display_(Seq[Any](format.raw/*61.27*/("""
  """),format.raw/*62.3*/("""Oops, something is <span style="color:"""),_display_(/*62.42*/color),format.raw/*62.47*/("""">wrong</span>
""")))}),format.raw/*63.2*/("""

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
                  DATE: Mon Feb 05 16:29:07 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/ListResource_Get.scala.html
                  HASH: 9eb50c6eded7544abaa7ff2b7dca745a606221ff
                  MATRIX: 500->1|666->72|694->75|716->89|755->91|783->93|881->165|892->168|923->179|1092->321|1152->365|1192->367|1241->388|1273->393|1283->394|1335->425|1389->448|1430->461|1517->521|1554->542|1594->544|1635->557|1700->595|1710->596|1744->609|1807->645|1817->646|1849->657|1896->677|1906->678|1940->691|1970->694|1980->695|2012->706|2102->769|2112->770|2143->780|2215->825|2225->826|2256->836|2356->905|2398->919|2554->1048|2566->1051|2596->1060|2632->1069|2735->1145|2747->1148|2780->1160|2877->1230|2947->1279|3024->1329|3057->1353|3109->1367|3138->1368|3174->1373|3210->1382|3272->1417|3319->1443|3355->1452|3483->1554|3506->1568|3555->1579|3585->1582|3651->1621|3677->1626|3723->1642
                  LINES: 12->1|17->1|19->3|19->3|19->3|21->5|23->7|23->7|23->7|28->12|28->12|28->12|29->13|29->13|29->13|29->13|30->14|31->15|34->18|34->18|34->18|35->19|36->20|36->20|36->20|37->21|37->21|37->21|37->21|37->21|37->21|37->21|37->21|37->21|39->23|39->23|39->23|40->24|40->24|40->24|43->27|45->29|53->37|53->37|53->37|54->38|58->42|58->42|58->42|60->44|60->44|64->48|64->48|64->48|64->48|64->48|65->49|68->52|68->52|69->53|77->61|77->61|77->61|78->62|78->62|78->62|79->63
                  -- GENERATED --
              */
          