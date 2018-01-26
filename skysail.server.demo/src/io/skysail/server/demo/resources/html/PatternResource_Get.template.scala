
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html._

object PatternResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.57*/("""

"""),_display_(/*10.2*/main(response)/*10.16*/ {_display_(Seq[Any](format.raw/*10.18*/("""

"""),format.raw/*12.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">

        <h1><a href="/demo/v1/patterns">Patterns</a> &gt; """),_display_(/*17.60*/rep/*17.63*/.getString(".id")),format.raw/*17.80*/("""</h1>
        <p class="lead">show pattern:</p>

        ID:   """),_display_(/*20.16*/rep/*20.19*/.getString(".id")),format.raw/*20.36*/("""<br>
        FROM: <a href='../accounts/"""),_display_(/*21.37*/rep/*21.40*/.getString(".from.id")),format.raw/*21.62*/("""'>"""),_display_(/*21.65*/rep/*21.68*/.getString(".from.title")),format.raw/*21.93*/("""</a><br>
        TO: <a href='../accounts/"""),_display_(/*22.35*/rep/*22.38*/.getString(".to.id")),format.raw/*22.58*/("""'>"""),_display_(/*22.61*/rep/*22.64*/.getString(".to.title")),format.raw/*22.87*/("""</a><br>
        AMOUNT:   """),_display_(/*23.20*/rep/*23.23*/.getString(".amount")),format.raw/*23.44*/("""<br>

    </div>

    <div class="card" style="width: 18rem;">
        <img class="card-img-top" src="..." alt="Card image cap">
        <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
    </div>

    <span>
   ***     """),_display_(/*37.13*/rep/*37.16*/.getString("")),format.raw/*37.30*/("""
        """),format.raw/*38.9*/("""</span>

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
                  DATE: Thu Jan 25 18:04:22 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PatternResource_Get.scala.html
                  HASH: 1abacffd030c33b55863fa732ce84a68a920fa3b
                  MATRIX: 187->193|550->209|700->264|729->267|752->281|792->283|821->285|981->418|993->421|1031->438|1122->502|1134->505|1172->522|1240->563|1252->566|1295->588|1325->591|1337->594|1383->619|1453->662|1465->665|1506->685|1536->688|1548->691|1592->714|1647->742|1659->745|1701->766|2187->1225|2199->1228|2234->1242|2270->1251
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|27->17|27->17|27->17|30->20|30->20|30->20|31->21|31->21|31->21|31->21|31->21|31->21|32->22|32->22|32->22|32->22|32->22|32->22|33->23|33->23|33->23|47->37|47->37|47->37|48->38
                  -- GENERATED --
              */
          