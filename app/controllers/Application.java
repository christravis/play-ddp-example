package controllers;

import actors.SockJSActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.libs.F.Function;
import play.mvc.*;
import play.mvc.Http.Context;
import play.sockjs.SockJS;
import play.sockjs.SockJSRouter;

public class Application extends Controller
{

	public static Result index()
	{
		return ok(views.html.ws.render());
	}

	public static SockJSRouter api = SockJSRouter.tryAccept(new Function<Context, SockJS>(){
		@Override
		public SockJS apply(Context context) throws Throwable {
			System.out.println("Got SockJS connection from: " + context.request().remoteAddress());
			//return SockJS.reject(Result);

			context.request().setUsername("chris");

			return SockJS.withActor(new Function<ActorRef,Props>(){
				@Override
				public Props apply(ActorRef out) throws Throwable {
					return SockJSActor.props(out, context.request());
				}
			});
		}
	});

}