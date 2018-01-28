
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
        <h1>Apps</h1>
        <!--<p class="lead">all notes:</p>-->

        <div class="row">
            """),_display_(/*24.14*/for((item, i) <- rep.rawData.zipWithIndex) yield /*24.56*/ {_display_(Seq[Any](format.raw/*24.58*/("""
              """),_display_(/*25.16*/if(rep.getString(s"[$i].name") != "_root")/*25.58*/ {_display_(Seq[Any](format.raw/*25.60*/("""
                """),format.raw/*26.17*/("""<div class="col">
                    <div class="card" style="width: 18rem;">
                        <!--<img class="card-img-top" src="..." alt="Card image cap">-->

                        <div class="card-body">
                            <h5 class="card-title"><i class="fa fa-adjust" aria-hidden="true"></i> """),_display_(/*31.101*/rep/*31.104*/.getString(s"[$i].name")),format.raw/*31.128*/("""</h5>
                            <h6 class="card-subtitle mb-2 text-muted">"""),_display_(/*32.72*/rep/*32.75*/.getString(s"[$i].version")),format.raw/*32.102*/("""</h6>
                            <p class="card-text">"""),_display_(/*33.51*/rep/*33.54*/.getString(s"[$i].description")),format.raw/*33.85*/("""</p>
                            <a href='"""),_display_(/*34.39*/rep/*34.42*/.getString(s"[$i].context")),format.raw/*34.69*/("""' class="btn btn-primary">Open</a>
                        </div>
                    </div>
                </div>
              """)))}),format.raw/*38.16*/("""
            """)))}),format.raw/*39.14*/("""
        """),format.raw/*40.9*/("""</div>

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
                  DATE: Fri Jan 26 19:57:52 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.app.appboard/./src/io/skysail/server/app/appboard/AppsResource_Get.scala.html
                  HASH: 4b7442bd173066cb5a586a6e80eb1c18b7465b2e
                  MATRIX: 185->193|545->209|695->264|724->267|747->281|787->283|816->285|952->394|964->397|999->411|1035->420|1181->539|1239->581|1279->583|1322->599|1373->641|1413->643|1458->660|1803->977|1816->980|1862->1004|1966->1081|1978->1084|2027->1111|2110->1167|2122->1170|2174->1201|2244->1244|2256->1247|2304->1274|2466->1405|2511->1419|2547->1428
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|28->18|28->18|28->18|29->19|34->24|34->24|34->24|35->25|35->25|35->25|36->26|41->31|41->31|41->31|42->32|42->32|42->32|43->33|43->33|43->33|44->34|44->34|44->34|48->38|49->39|50->40
                  -- GENERATED --
              */
          