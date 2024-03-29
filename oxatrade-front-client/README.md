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

## React Bootstrap Icons

```
bun add react-bootstrap-icons
```

### Usage:

```
import { ArrowRight } from 'react-bootstrap-icons';

export default function App() {
  return <ArrowRight />;
}
```

or:

```
import * as Icon from 'react-bootstrap-icons';

export default function App() {
  return <Icon.ArrowRight />;
}
```

Icons can be configured with inline props:

```
<ArrowRight className="ml-4" color="royalblue" size={96} />
```

<b>Documentation:</b> https://www.npmjs.com/package/react-bootstrap-icons

___

## react-toastify

With react toastify you can easily create toast notifications and alerts in your react application

<b>Documentation:</b> https://deadsimplechat.com/blog/react-toastify-the-complete-guide/
___

## Axios

Next, install the [Axios](https://github.com/axios/axios) dependency as follows:

```
bun add axios
```

Notice that we installed Axios as a dependency. Axios is a promise-based HTTP client for the browser and Node.js. It will enable us to fetch posts from the [Bacon Ipsum JSON API](https://baconipsum.com/json-api/).

<b>Tutorial for Axios:</b> https://blog.logrocket.com/how-to-make-http-requests-like-a-pro-with-axios/#new_tab

___

### React Router

To add React Router in your application, run this in the terminal from the root directory of the application:

```
bun add react-router-dom
```

<b>Tutorial for React Router:</b> https://www.w3schools.com/react/react_router.asp

___

### Yup

Yup is a popular validation library for Reactjs that makes it easy to create and manage form validation.

```
bun add yup
```

```
import * as Yup from 'yup';

const schema = Yup.object().shape({
  email: Yup.string().email('Invalid email').required('Email is required'),
  password: Yup.string().min(8, 'Password must be at least 8 characters').required('Password is required'),
});

const handleSubmit = (e) => {
  e.preventDefault();

  const formData = {
    email: e.target.email.value,
    password: e.target.password.value,
  };

  const errors = schema.validate(formData);

  if (errors.length > 0) {
    // There are errors in the form data
    alert(errors.join("\n"));
  } else {
    // The form data is valid, do something with it
  }
};
```
___

### formik

Formik is the world's most popular open source form library for React and React Native.

<b>Documentation:</b> https://formik.org/

<b>A React component with reactstrap, formik, and yup:</b> https://gist.github.com/AndrewBedell/b5f650475012f62bbbdf6ffbdf3711da

<b>Example:</b> https://blog.bitsrc.io/exploring-yup-a-powerful-validation-library-for-react-172ed02be464

___

### react-select

A flexible and beautiful Select Input control for ReactJS with multiselect, autocomplete, async and creatable support.

```
bun add react-select
```

<b>Styles on GitHub:</b> https://github.com/JedWatson/react-select/blob/90995cca51e59c6691d35ec2862a62bbd02f68c2/packages/react-select/src/styles.js

<b>Documentation:</b> https://react-select.com

___

### Social Login

Implementing social login (e.g., Google, Facebook, or GitHub) in a Spring Boot and React application involves integrating OAuth 2.0 authentication flow on the backend and handling authentication on the frontend.

<b>Tutorial:</b> https://rathoreaparna678.medium.com/implementing-social-login-in-a-spring-boot-and-react-app-afcf5bec3f73

- Add the required packages to your React project using bun `react-google-login`, `react-facebook-login`, or other social login libraries for the specific OAuth providers you want to support.
- Create a Login component to handle the social login buttons and user authentication

___

### Authentication with React Router v6

<b>Tutorial:</b> https://blog.logrocket.com/complete-guide-authentication-with-react-router-v6/
