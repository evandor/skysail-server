
package io.skysail.server.app.appboard.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html._

object AppsResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.57*/("""

"""),_display_(/*10.2*/main(response)/*10.16*/ {_display_(Seq[Any](format.raw/*10.18*/("""

"""),format.raw/*12.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
    
       <!-- <span>
   ***     """),_display_(/*18.13*/rep/*18.16*/.getString("")),format.raw/*18.30*/("""
        """),format.raw/*19.9*/("""</span>-->
        <h1>Dashboard</h1>
        <!--<p class="lead">all notes:</p>-->

        <div class="row">
            """),_display_(/*24.14*/for((item, i) <- rep.rawData.zipWithIndex) yield /*24.56*/ {_display_(Seq[Any](format.raw/*24.58*/("""
              """),_display_(/*25.16*/if(rep.getString(s"[$i].name") != "_root")/*25.58*/ {_display_(Seq[Any](format.raw/*25.60*/("""
                """),format.raw/*26.17*/("""<div class="col">
                    <div class="card" style="width: 18rem;">
                        <!--<img class="card-img-top" src="..." alt="Card image cap">-->
                        <div class="card-body">
                            <h5 class="card-title">"""),_display_(/*30.53*/rep/*30.56*/.getString(s"[$i].name")),format.raw/*30.80*/("""</h5>
                            <p class="card-text">"""),_display_(/*31.51*/rep/*31.54*/.getString(s"[$i].description")),format.raw/*31.85*/("""</p>
                            <a href='"""),_display_(/*32.39*/rep/*32.42*/.getString(s"[$i].context")),format.raw/*32.69*/("""' class="btn btn-primary">Open</a>
                        </div>
                    </div>
                </div>
              """)))}),format.raw/*36.16*/("""
            """)))}),format.raw/*37.14*/("""
        """),format.raw/*38.9*/("""</div>

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
                  DATE: Fri Jan 26 17:09:02 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.app.appboard/./src/io/skysail/server/app/appboard/AppsResource_Get.scala.html
                  HASH: 3593e06089666fae9f8c8b405333b0c43dc57bab
                  MATRIX: 185->193|545->209|695->264|724->267|747->281|787->283|816->285|952->394|964->397|999->411|1035->420|1186->544|1244->586|1284->588|1327->604|1378->646|1418->648|1463->665|1758->933|1770->936|1815->960|1898->1016|1910->1019|1962->1050|2032->1093|2044->1096|2092->1123|2254->1254|2299->1268|2335->1277
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|28->18|28->18|28->18|29->19|34->24|34->24|34->24|35->25|35->25|35->25|36->26|40->30|40->30|40->30|41->31|41->31|41->31|42->32|42->32|42->32|46->36|47->37|48->38
                  -- GENERATED --
              */
          