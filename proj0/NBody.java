public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);

		int n = in.readInt();
		double r = in.readDouble();

		return r;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);

		int n = in.readInt();
		double r = in.readDouble();
		Planet[] fivep = new Planet[n];

		for (int i = 0; i < n; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			fivep[i] = new Planet(xxPos, yyPos, xxVel, yyVel,
				                     mass, imgFileName);
		}

		return fivep;
	}

	public static void main(String[] args) {
		  double T = Double.parseDouble(args[0]);
		  double dt = Double.parseDouble(args[1]);
		  String filename = args[2];
		  double radius = NBody.readRadius(filename);
          Planet[] planets = NBody.readPlanets(filename);
          String starfield = "./images/starfield.jpg";

          int i = 0;
		  double time =0;
		  double[] xForces = new double[planets.length];
          double[] yForces = new double[planets.length];

          StdDraw.enableDoubleBuffering();

          StdDraw.setScale(-radius, radius);

          StdDraw.clear();

          StdDraw.picture(0, 0, starfield);

          for (i = 0; i < planets.length; i++) {
          	planets[i].draw();
          }

          while (time <= T) {

          	for (i = 0; i < planets.length; i++) {
          		xForces[i] = planets[i].calcNetForceExertedByX(planets);
          		yForces[i] = planets[i].calcNetForceExertedByY(planets);
          	}

            for (i = 0; i < planets.length; i++) {
          	    planets[i].update(dt, xForces[i], yForces[i]);
          	}

          	StdDraw.picture(0, 0, starfield);

          	for (i = 0; i < planets.length; i++) {
          	    planets[i].draw();
            }

            StdDraw.show();

            StdDraw.pause(1);

            time += dt;
          }

          StdOut.printf("%d\n", planets.length);
          StdOut.printf("%.2e\n", radius);

          for (i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
          }
	}
}