import Home from "../pages/Home";
import About from "../pages/About";
import Account from "../pages/Account";
import ProductDetails from "../pages/ProductDetails";
import SignIn from "../pages/Login/SignIn";
import SignUp from "../pages/Login/SignUp";
import Contact from "../pages/Contact/Contact";
import Product from "../pages/Product/Product";

const publicRoute = [
  { path: "/", component: Home },
  { path: "/home", component: Home },
  { path: "/about", component: About },
  { path: "/account", component: Account },
  { path: "/product", component: Product },
  { path: "/productdetail", component: ProductDetails },
  { path: "/login", component: SignIn },
  { path: "/register", component: SignUp },
  { path: "/contact", component: Contact }
];
const privateRoute = [];
export { publicRoute, privateRoute };
