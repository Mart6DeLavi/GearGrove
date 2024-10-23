import React from 'react';
import { useParams } from 'react-router-dom';
import styles from './Product.module.css';

interface ProductInfo {
    [key: string]: {
        name: string;
        description: string;
        price: number;
        image: string;
        specs: { [key: string]: string };
        reviews: { author: string; rating: number; comment: string }[];
    };
}

const productInfo: ProductInfo = {
    '1': {
        name: 'RTX 3080 Graphics Card',
        description: 'High-performance graphics card for gaming and content creation.',
        price: 699.99,
        image: '/placeholder.svg?height=400&width=600',
        specs: {
            'CUDA Cores': '8704',
            'Boost Clock': '1.71 GHz',
            'Memory': '10GB GDDR6X',
            'Memory Interface': '320-bit',
        },
        reviews: [
            { author: 'John D.', rating: 5, comment: 'Amazing performance, highly recommended!' },
            { author: 'Sarah M.', rating: 4, comment: 'Great card, but runs a bit hot.' },
        ],
    },
    // Add more products as needed
};

const Product: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const product = id && productInfo[id];

    if (!product) {
        return (
            <div className="text-center">
                <h1 className="text-3xl font-bold mb-6">Product Not Found</h1>
                <p>Sorry, we couldn't find the product you're looking for.</p>
            </div>
        );
    }

    return (
        <div className={styles.container}>
            <div className={styles.productHeader}>
                <div>
                    <img className={styles.productImage} src={product.image} alt={product.name} />
                </div>
                <div className={styles.productInfo}>
                    <div className={styles.productName}>{product.name}</div>
                    <p className={styles.productDescription}>{product.description}</p>
                    <p className={styles.productPrice}>${product.price.toFixed(2)}</p>
                    <button className={styles.addToCartButton}>
                        Add to Cart
                    </button>
                </div>
            </div>
            <div className={styles.section}>
                <h2 className={styles.sectionTitle}>Specifications</h2>
                <dl className={styles.specsList}>
                    {Object.entries(product.specs).map(([key, value]) => (
                        <div key={key} className={styles.specItem}>
                            <dt className={styles.specLabel}>{key}</dt>
                            <dd className={styles.specValue}>{value}</dd>
                        </div>
                    ))}
                </dl>
            </div>
            <div className={styles.section}>

                <h2 className={styles.sectionTitle}>Customer Reviews</h2>
                <div className={styles.reviewList}>
                    {product.reviews.map((review, index) => (
                        <div key={index} className={styles.reviewItem}>
                            <div className={styles.reviewHeader}>
                                <span className={styles.reviewAuthor}>{review.author}</span>
                                <span className={styles.reviewRating}>{'★'.repeat(review.rating)}{'☆'.repeat(5 - review.rating)}</span>
                            </div>
                            <p className={styles.reviewComment}>{review.comment}</p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Product;