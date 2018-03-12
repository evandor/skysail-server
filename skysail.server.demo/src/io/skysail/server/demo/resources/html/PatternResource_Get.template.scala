
package io.skysail.server.demo.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel
/*6.2*/import html._

object PatternResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.68*/("""

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

  def render(rep:RepresentationModel,response:ResponseEventBase,o:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,o)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,o) => apply(rep,response,o)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Mar 12 18:26:54 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.demo/./src/io/skysail/server/demo/resources/PatternResource_Get.scala.html
                  HASH: 61b3efda3b58c86da91318b794bcba44bc92d60f
                  MATRIX: 187->193|557->209|718->275|747->278|770->292|810->294|839->296|999->429|1011->432|1049->449|1140->513|1152->516|1190->533|1258->574|1270->577|1313->599|1343->602|1355->605|1401->630|1471->673|1483->676|1524->696|1554->699|1566->702|1610->725|1665->753|1677->756|1719->777|2205->1236|2217->1239|2252->1253|2288->1262
                  LINES: 8->6|13->8|18->8|20->10|20->10|20->10|22->12|27->17|27->17|27->17|30->20|30->20|30->20|31->21|31->21|31->21|31->21|31->21|31->21|32->22|32->22|32->22|32->22|32->22|32->22|33->23|33->23|33->23|47->37|47->37|47->37|48->38
                  -- GENERATED --
              */
          