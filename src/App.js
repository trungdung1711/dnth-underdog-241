import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { publicRoute } from "./routes";
import DefaultLayout from "./components/UserLayout/DefaultLayout";

function App() {
  return (
    <Router>
      <Routes>
        {publicRoute.map((route, index) => {
          const Layout = DefaultLayout;
          const Page = route.component;
          return <Route key={index} path={route.path} element={<Page />} />;
        })}
      </Routes>
    </Router>
  );
}

export default App;
