package com.pkch.pharos;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.crypto.Cipher;

import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		
		
		PrivateKey privatekey = null;
		PublicKey publickey = null;
		
		try {
			
			KeyStore ks = null;
			char[] password = null;

			Security.addProvider(new BouncyCastleProvider());
			try {
				ks = KeyStore.getInstance("PKCS12");
				// Password pour le fichier personnal_nyal.p12
				password = "2[$0wUOS".toCharArray();
				ks.load(new FileInputStream("D:\\tmp\\personnal.p12"), password);
			} catch (Exception e) {
				System.out.println("Erreur: fichier personnal.p12 n'est pas un fichier pkcs#12 valide ou passphrase incorrect");
				return ;
			}

			// RECUPERATION DU COUPLE CLE PRIVEE/PUBLIQUE ET DU CERTIFICAT PUBLIQUE

			X509Certificate cert = null;
			//PrivateKey privatekey = null;
			//PublicKey publickey = null;

			try {
				Enumeration en = ks.aliases();
				String ALIAS = "";
				Vector vectaliases = new Vector();

				while (en.hasMoreElements())
					vectaliases.add(en.nextElement());
				String[] aliases = (String []) (vectaliases.toArray(new String[0]));
				for (int i = 0; i < aliases.length; i++)
					if (ks.isKeyEntry(aliases[i]))
					{
						ALIAS = aliases[i];
						break;
					}
				privatekey = (PrivateKey)ks.getKey(ALIAS, password);
				cert = (X509Certificate)ks.getCertificate(ALIAS);
				publickey = ks.getCertificate(ALIAS).getPublicKey();
				
				System.out.println(cert.toString());
				
				
				// Recuperation de la taille de la clé
				RSAPublicKey rsaPk = (RSAPublicKey) cert.getPublicKey();
				System.out.println(rsaPk.getModulus().bitLength());
			} catch (Exception e) {
				e.printStackTrace();
				return ;
			}
			
				// Chargement du certificat à partir du fichier
//
//				InputStream inStream = new FileInputStream("D:\\tmp\\personnal.cer");
//				CertificateFactory cf = CertificateFactory.getInstance("X.509");
//				/*X509Certificate */cert = (X509Certificate)cf.generateCertificate(inStream);
//				inStream.close();
//
//				// Affiche le contenu du certificat
//
//				System.out.println(cert.toString());
//			
			// Chargement du fichier qui va être signé

			File file_to_sign = new File("D:\\tmp\\test.txt");
			byte[] buffer = new byte[(int)file_to_sign.length()];
			DataInputStream in = new DataInputStream(new FileInputStream(file_to_sign));
			in.readFully(buffer);
			in.close();

			// Chargement des certificats qui seront stockés dans le fichier .p7
			// Ici, seulement le certificat personnal_nyal.cer sera associé.
			// Par contre, la chaîne des certificats non.

			ArrayList certList = new ArrayList();
			certList.add(cert);
			CertStore certs = CertStore.getInstance("Collection",
								new CollectionCertStoreParameters(certList), "BC");

			CMSSignedDataGenerator signGen = new CMSSignedDataGenerator();

			// privatekey correspond à notre clé privée récupérée du fichier PKCS#12
			// cert correspond au certificat publique personnal_nyal.cer
			// Le dernier argument est l'algorithme de hachage qui sera utilisé

			signGen.addSigner(privatekey, cert, CMSSignedDataGenerator.DIGEST_SHA1);
			signGen.addCertificatesAndCRLs(certs);
			CMSProcessable content = new CMSProcessableByteArray(buffer);

			// Generation du fichier CMS/PKCS#7
			// L'argument deux permet de signifier si le document doit être attaché avec la signature
			//     Valeur true:  le fichier est attaché (c'est le cas ici)
			//     Valeur false: le fichier est détaché

			CMSSignedData signedData = signGen.generate(content, true, "BC");
			byte[] signeddata = signedData.getEncoded();

			// Ecriture du buffer dans un fichier.	

			FileOutputStream envfos = new FileOutputStream("D:\\tmp\\" + file_to_sign.getName() + "_signe.pk7");
			envfos.write(signeddata);
			envfos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		
		try {
			// Chargement du fichier signé
			File f = new File("D:\\tmp\\test.txt_signe.pk7");
			byte[] buffer = new byte[(int)f.length()];
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			in.readFully(buffer);
			in.close();

			CMSSignedData signature = new CMSSignedData(buffer);
			SignerInformation signer = (SignerInformation)signature
		                .getSignerInfos().getSigners().iterator().next();
			CertStore cs = signature
		                .getCertificatesAndCRLs("Collection", "BC");
			Iterator iter = cs.getCertificates(signer.getSID()).iterator();
			X509Certificate certificate = (X509Certificate) iter.next();
			CMSProcessable sc = signature.getSignedContent();
			byte[] data = (byte[]) sc.getContent();

			// Verifie la signature
			System.out.println(signer.verify(certificate, "BC"));

			FileOutputStream envfos = new FileOutputStream("D:\\tmp\\test_non_signer.txt");
			envfos.write(data);
			envfos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		
		
		try {
	        // Chargement du fichier à chiffrer
	        File f = new File("D:\\tmp\\test.txt");
	        byte[] buffer = new byte[(int)f.length()];
	        DataInputStream in = new DataInputStream(new FileInputStream(f));
	        in.readFully(buffer);
	        in.close();

	        // Chiffrement du document
	        // Seul le mode ECB est possible avec RSA
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
	        // publickey est la cle publique du destinataire
	        cipher.init(Cipher.ENCRYPT_MODE, publickey,
	                    new SecureRandom("nyal rand".getBytes()));
	        int blockSize = cipher.getBlockSize();
	        int outputSize = cipher.getOutputSize(buffer.length);
	        int leavedSize = buffer.length % blockSize;
	        int blocksSize = leavedSize != 0 ?
	        buffer.length / blockSize + 1 : buffer.length / blockSize;
	        byte[] raw = new byte[outputSize * blocksSize];
	        int i = 0;
		while (buffer.length - i * blockSize > 0)
	        {
	                if (buffer.length - i * blockSize > blockSize)
	                        cipher.doFinal(buffer, i * blockSize, blockSize,
	                                       raw, i * outputSize);
	                else
	                        cipher.doFinal(buffer, i * blockSize,
	                                       buffer.length - i * blockSize,
	                                       raw, i * outputSize);
	                i++;
	        }

	        // Ecriture du fichier chiffré sur le disque dur
	        FileOutputStream envfos = new FileOutputStream("D:\\tmp\\" + f.getName() + "_chiffre.p1");
	        envfos.write(raw);
	        envfos.close();

	        // Déchiffrement du fichier
	        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
	        //
	        // La variable privatekey correspond à la clé privée associée à la clé
	        // publique précédente. (Voir la section sur le fichier PKCS#12 pour
	        // recupérer la clé privée)
	        //
	        cipher.init(cipher.DECRYPT_MODE, privatekey);
	        blockSize = cipher.getBlockSize();
	        ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
	        int j = 0;

	        while (raw.length - j * blockSize > 0)
	        {
	                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
	                j++;
	        }

	        envfos = new FileOutputStream("D:\\tmp\\fichier_dechiffre.txt");
	        envfos.write(bout.toByteArray());
	        envfos.close();

	} catch (Exception e) {
	        e.printStackTrace();
	}
		
		/*
		
		System.out.println("Connecting SFTP");

		JSch jsch = new JSch();
		Session session = null;
		try {
			jsch = new JSch();
			session = jsch.getSession("pkch", "192.168.1.33", 2222);
			session.setPassword("password");
			             
			Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			 
			session.connect();
			session.disconnect();
			
			System.out.println("Disconnected SFTP");
			
		} catch (JSchException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}*/
	}
	
	
}
