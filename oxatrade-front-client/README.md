# OxaTrade React + Vite Client App

This project was bootstrapped with [Bun](https://bun.sh/) and [Vite](https://vitest.dev/) on JavaScript-[SWC](https://swc.rs).

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

We take [SWC](https://swc.rs/).
___

To start, install Bun on your machine:

```
curl https://bun.sh/install | bash
```

Use the following command to check that Bun was successfully installed:

```
bun -v
```

There are several ways to start a React project — using Create React App, Vite, or even piecing it all together from scratch. While Bun allows you to bootstrap your application with starter templates like the ones we’ve mentioned, Vite is the preferred option, not only because it was designed to be fast, but also because it benefits from Bun optimization and speed magic.

```
bun create vite oxatrade-front-client
```
Select a framework: › React

Select a variant: › JavaScript

Done. Now run:

```
  cd oxatrade-client
  bun install
  bun run dev
```
If you want to create a production build, run this command:

```
bun react-scripts build
```

To start a React app with Bun and Vite, run:

```
bun vite
```

To run the program with Bun, use this command:

```
bun [file name]
```

Install all dependencies with

```
bun install
```

, or manage dependencies with

```
bun add [dependency]
```
and

```
bun remove [dependency]
```
___

## Customizing with Reactstrap

The [Reactstrap](https://www.npmjs.com/package/reactstrap) package is quite similar to [React-Bootstrap](https://www.npmjs.com/package/react-bootstrap), with minor differences in things such as component and prop names. React-Bootstrap is a little older than Reactstrap, which may have contributed to its wider adoption range.

We can easily add Reactstrap to our React project with the command below:

```
bun add bootstrap reactstrap
```

Once we have Reactstrap installed, we are able to import any component. For example, importing the Button and Dropdown component would look like this:

```
import { Button, Dropdown } from 'reactstrap';
```

( @see code for our ThemeSwitcher component )

<b>Tutorial for Axios:</b> https://blog.logrocket.com/using-bootstrap-react-tutorial-examples/

___

## Axios

Next, install the [Axios](https://github.com/axios/axios) dependency as follows:

```
bun add axios
```

Notice that we installed Axios as a dependency. Axios is a promise-based HTTP client for the browser and Node.js. It will enable us to fetch posts from the [Bacon Ipsum JSON API](https://baconipsum.com/json-api/).

<b>Tutorial for Axios:</b> https://blog.logrocket.com/how-to-make-http-requests-like-a-pro-with-axios/#new_tab

