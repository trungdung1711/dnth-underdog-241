import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import Header from "../../components/UserLayout/DefaultLayout/Header";
import CategorySlider from "./CategorySlider";
import ListCard from "./ListCard";
import ListCard1 from "./ListCard1";
import ListCard2 from "./ListCard2";
import Banner from "./Banner";
import Category from "./Category";

function Home() {
  return (
    <div>
      <Header />
      <CategorySlider />
      <ListCard1 />
      <Banner />
      <Category />
      <ListCard2 />
      <ListCard />
      <Footer />
    </div>
  );
}

export default Home;
