import Home from "../pages/Home";
import About from "../pages/About";
import Account from "../pages/Account";
import ProductDetails from "../pages/ProductDetails";
const publicRoute = [
  { path: "/", component: Home },
  { path: "/about", component: About },
  { path: "/account", component: Account },
  { path: "/productDetail", component: ProductDetails },
];
const privateRoute = [];
export { publicRoute, privateRoute };
