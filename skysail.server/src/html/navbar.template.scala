
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object navbar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a id="navtitle" class="navbar-brand col-sm-3 col-md-2 mr-0" href="/">skysail</a>
    <!--<input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">-->
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
        </li>
    </ul>
</nav>

<script>
  $.get( "/_root", function( data ) """),format.raw/*12.37*/("""{"""),format.raw/*12.38*/("""
    """),format.raw/*13.5*/("""$( "#navtitle" ).html( data.description );
  """),format.raw/*14.3*/("""}"""),format.raw/*14.4*/(""", 'json');

</script>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Apr 03 20:22:14 CEST 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server/./src/navbar.scala.html
                  HASH: 67f910c054fc6e75d083fd20f7680686a24302ac
                  MATRIX: 534->0|1042->480|1071->481|1103->486|1175->531|1203->532
                  LINES: 17->1|28->12|28->12|29->13|30->14|30->14
                  -- GENERATED --
              */
          