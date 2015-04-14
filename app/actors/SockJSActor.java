package actors;

import play.libs.Akka;
import play.mvc.Http.Request;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.EventStream;

public class SockJSActor extends UntypedActor {

	public static Props props (ActorRef out, Request request)
	{
		return Props.create(SockJSActor.class, out, request);
	}

	private final ActorRef out;
	private Request request;

	public SockJSActor(ActorRef out, Request request)
	{
		this.out = out;
		this.request = request;
	}

	@Override
	public void preStart() throws Exception
	{
		System.out.println("Starting up actor for: " + this.self().path().toString());
		System.out.println("Starting up actor for: " + this.request.username());
		System.out.println("Parent is: " + this.getContext().parent().path());
	}

	@Override
	public void onReceive(Object message) throws Exception
	{
		if (message instanceof String) {
			System.out.println(this.self().path().toString() + " got msg:");
			System.out.println(message);
			this.out.tell("Thanks for sending me: " + message, this.self());

			if (message.equals("actors")) {
				// @TODO
			}
		} else {
			unhandled(message);
		}
	}

	@Override
	public void postStop() throws Exception
	{
		System.out.println("Stopping actor for: " + this.self().path().toString());
	}

}