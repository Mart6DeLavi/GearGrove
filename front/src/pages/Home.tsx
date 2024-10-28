import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Home.module.css';

const featuredProducts = [
    { id: 1, name: 'RTX 3080 Graphics Card', price: 699.99, image: '/products/3080.png' },
    { id: 2, name: 'Ryzen 9 5950X Processor', price: 749.99, image: '/products/r9-5950x.png' },
    { id: 3, name: '32GB DDR4 RAM Kit', price: 159.99, image: '/products/ram.png' },
    { id: 4, name: '1TB NVMe SSD', price: 129.99, image: '/products/ssd.png' },
];

const Home: React.FC = () => {
    return (
        <div className={styles.container}>
            <section className={styles.hero}>
                <h1 className={styles.heroTitle}>Welcome to GearGrove</h1>
                <p className={styles.heroSubtitle}>Your one-stop shop for premium computer parts</p>
            </section>

            <section className={styles.featuredSection}>
                <h2 className={styles.featuredTitle}>Featured Products</h2>
                <div className={styles.productGrid}>
                    {featuredProducts.map((product) => (
                        <div key={product.id} className={styles.productCard}>
                            <img src={product.image} alt={product.name} className={styles.productImage} />
                            <div className={styles.productInfo}>
                                <h3 className={styles.productName}>{product.name}</h3>
                                <p className={styles.productPrice}>${product.price.toFixed(2)}</p>
                                <Link
                                    to={`/product/${product.id}`}
                                    className={styles.viewDetailsButton}
                                >
                                    View Details
                                </Link>
                            </div>
                        </div>
                    ))}
                </div>
            </section>

            {/*<section className={styles.ctaSection}>*/}
            {/*    <h2 className={styles.ctaTitle}>Build Your Dream PC Today</h2>*/}
            {/*    <p className="mb-6">Explore our wide range of high-quality components</p>*/}
            {/*    <Link*/}
            {/*        to="/products"*/}
            {/*        className={styles.ctaButton}*/}
            {/*    >*/}
            {/*        Shop All Parts*/}
            {/*    </Link>*/}
            {/*</section>*/}
        </div>
    );
};

export default Home;